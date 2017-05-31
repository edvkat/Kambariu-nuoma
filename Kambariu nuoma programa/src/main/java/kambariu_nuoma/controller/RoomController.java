package kambariu_nuoma.controller;

import kambariu_nuoma.dao.RoomDAO;
import kambariu_nuoma.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RoomController {

    private static final int NUMBER_OF_ROOMS_IN_PAGE = 8;
    RoomDAO roomDAO;

    @Autowired
    public RoomController(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @GetMapping("/availableRooms")
    public String getAvailableRooms() {
        return String.format("redirect:/availableRooms/%s/0", NUMBER_OF_ROOMS_IN_PAGE);
    }

    @GetMapping("/availableRooms/{numberOfRoomsInPage}/{offset}")
    public String getAvailableRooms(@PathVariable int numberOfRoomsInPage, @PathVariable int offset, ModelMap modelMap) {
        List<Room> allRooms = roomDAO.getAllAvailableRooms(numberOfRoomsInPage, offset);
        int numberOfAvailableRooms = roomDAO.getNumberOfAvailableRooms();
        modelMap.put("numberOfRooms", numberOfAvailableRooms);
        modelMap.put("numberOfRoomsInPage", numberOfRoomsInPage);
        modelMap.put("offeredRooms", allRooms);
        return "offeredRooms";
    }

    @GetMapping("/roomInfo/id={roomID}")
    public String getRoomInfoView(@PathVariable int roomID, ModelMap modelMap) {
        Room room = roomDAO.getRoom(roomID);
        modelMap.put("room", room);
        return "roomInfo";
    }
}
