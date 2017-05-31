package kambariu_nuoma.controller;

        import kambariu_nuoma.dao.RentContractDAO;
        import kambariu_nuoma.dao.RoomDAO;
        import kambariu_nuoma.model.ContractValidator;
        import kambariu_nuoma.model.RentContract;
        import kambariu_nuoma.model.Room;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;

        import javax.servlet.http.HttpSession;
        import java.util.List;
        import java.util.ResourceBundle;

@Controller
public class ContractController {

    private RoomDAO roomDAO;
    private RentContractDAO contractDAO;
    private ResourceBundle resources;
    private ContractValidator contractValidator;

    @Autowired
    public ContractController(RoomDAO roomDAO, RentContractDAO contractDAO, ResourceBundle resources, ContractValidator contractValidator) {
        this.roomDAO = roomDAO;
        this.contractDAO = contractDAO;
        this.resources = resources;
        this.contractValidator = contractValidator;
    }

    @GetMapping("/createContract/id={roomID}")
    public String createContract (@PathVariable int roomID, HttpSession session, ModelMap modelMap) {
        String username = (String)session.getAttribute("username");
        RentContract contract = new RentContract(roomID, username);
        contract.setRoomID(roomID);
        contract.setUsername(username);
        Room room = roomDAO.getRoom(roomID);
        modelMap.put("room", room);
        modelMap.put("rentContract", contract);
        return "contractForm";
    }

    @PostMapping("/createContract/id={roomID}")
    public String createContract (@PathVariable int roomID, @ModelAttribute RentContract rentContract, BindingResult result, HttpSession session, ModelMap modelMap ) {
        Room room = roomDAO.getRoom(roomID);
        int newContractStatus = rentContract.validate();
        if (result.hasErrors()) {
            return createContract(room.getId(), session, modelMap);
        } else if (newContractStatus <= 0) {
            modelMap.put("rentFailure", newContractStatus);
            return createContract(room.getId(), session, modelMap);
        } else {
            rentContract.setTotalPrice(room.getPriceForDay(), rentContract.getNumberOfDays());
            roomDAO.editRoom( roomID, false, rentContract.getToDate());
            contractDAO.insertContract(rentContract);
            return "redirect:/availableRooms";
        }
    }

    @GetMapping("/validContracts/{numberOfContractsInPage}/{offset}")
    public String getValidContraacts(@PathVariable int numberOfContractsInPage, @PathVariable int offset, ModelMap modelMap, HttpSession session) {
        String username = (String)session.getAttribute("username");
        List<RentContract> validContracts = contractDAO.getValidRentContracts(username, numberOfContractsInPage, offset);
        int numberOfValidRentContracts = contractDAO.getNumberOfValidRentContracts(username);
        modelMap.put("contracts", validContracts);
        modelMap.put("numberOfContractsInPage", numberOfContractsInPage);
        modelMap.put("numberOfContracts", numberOfValidRentContracts);
        modelMap.put("noElements", resources.getString("msg.noValidContracts"));
        modelMap.put("tableHeader", resources.getString("msg.validContractsList") /*"Tebegaliojančių nuomos sutarčių sąrašas"*/);
        return "contracts";
    }

    @GetMapping("/voidContracts/{numberOfContractsInPage}/{offset}")
    public String getVoidContraacts(@PathVariable int numberOfContractsInPage, @PathVariable int offset, ModelMap modelMap, HttpSession session) {
        String username = (String)session.getAttribute("username");
        List<RentContract> voidRentCcontracts = contractDAO.getVoidRentContracts(username, numberOfContractsInPage, offset);
        int numberOfVoidRentContracts = contractDAO.getNumberOfVoidRentContracts(username);
        modelMap.put("contracts", voidRentCcontracts);
        modelMap.put("numberOfContractsInPage", numberOfContractsInPage);
        modelMap.put("numberOfContracts", numberOfVoidRentContracts);
        modelMap.put("noElements", resources.getString("msg.noVoidContracts"));
        modelMap.put("tableHeader", resources.getString("msg.voidContractsList")/* "Pasibaigusių nuomos sutarčių sąrašas"*/);
        return "contracts";
    }
}
