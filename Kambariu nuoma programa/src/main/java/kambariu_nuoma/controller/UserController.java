package kambariu_nuoma.controller;

import kambariu_nuoma.dao.UserDAO;
import kambariu_nuoma.model.LoginBean;
import kambariu_nuoma.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

@Controller
public class UserController {

    ResourceBundle resources;
    UserDAO userDAO;

    @Autowired
    public UserController(ResourceBundle resources, UserDAO userDAO) {
        this.resources = resources;
        this.userDAO = userDAO;
    }

    @GetMapping("/login")
    public String login(ModelMap modelMap) {
        LoginBean loginBean = new LoginBean();
        modelMap.put("loginBean", loginBean);
        return "loginForm";
    }

    @PostMapping("/login")
    public String login (LoginBean loginBean, HttpSession session, ModelMap modelMap) {
        boolean loginSuccess = userDAO.validateLogin(loginBean);
        if (loginSuccess) {
            session.setAttribute("username", loginBean.getUsername());
            session.removeAttribute("loginFailed");
            return "redirect:/";
        } else {
            session.setAttribute("loginFailed", true);
            modelMap.put("loginBean", loginBean);
            modelMap.put("errorMessage", resources.getString("msg.loginFailed"));
            return "loginForm";
        }
    }

    @GetMapping("/logout")
    public String logout (HttpSession session) {
        session.removeAttribute("username");
        return "index";
    }

    @GetMapping("/register")
    public String register (ModelMap modelMap) {
        User user = new User();
        modelMap.put("newUser", user);
        return "registerForm";
    }

    @PostMapping("/register")
    public String registerprocess (User newUser, HttpSession session, ModelMap modelMap) {
        int status = userDAO.validateUser(newUser);
        if (status > 0) {
            session.setAttribute("username", newUser.getLogin());
            session.removeAttribute("registerFailed");
            userDAO.insertUser(newUser);
            return "redirect:/";
        } else {
            session.setAttribute("registerFailed", true);
            modelMap.put("newUser", new User());
            switch (status) {
                case -1:
                    modelMap.put("errorMessage", resources.getString("msg.invalidUsername"));
                    break;
                case -2:
                    modelMap.put("errorMessage", resources.getString("msg.invalidEmail"));
                    break;
                case -3:
                    modelMap.put("errorMessage", resources.getString("msg.invalidPassword"));
                    break;
                case -4:
                    modelMap.put("errorMessage", resources.getString("msg.userExists"));
                    break;
                case -5:
                    modelMap.put("errorMessage", resources.getString("msg.invalidFirstName"));
                    break;
                case -6:
                    modelMap.put("errorMessage", resources.getString("msg.invalidLastName"));
                    break;
                case -7:
                    modelMap.put("errorMessage", resources.getString("msg.invalidLivingLocation"));
                    break;
                default:
                    modelMap.put("errorMessage", resources.getString("msg.unexpectedError"));
            }
            return "registerForm";
        }
    }

    @GetMapping("/profile")
    public String profile () {
        return "profile";
    }


    @GetMapping("/editProfile")
    public String editUser (HttpSession session, ModelMap modelMap) {
        User existingUser = userDAO.getUser((String)session.getAttribute("username"));
        modelMap.put("existingUser", existingUser);
        return "editUserForm";
    }

    @PostMapping("/editProfile")
    public String editUser(@ModelAttribute User existingUser, ModelMap modelMap, HttpSession session) {
        int status = userDAO.validateExistingUser(existingUser);
        if (status > 0) {
            session.setAttribute("username", existingUser.getLogin());
            session.removeAttribute("editUserFailed");
            userDAO.editUser(existingUser.getLogin(), existingUser.getFirstName(), existingUser.getLastName(), existingUser.getLivingLocation(), existingUser.getEmail());
            return "redirect:/";
        } else {
            session.setAttribute("editUserFailed", true);
            modelMap.put("existingUser", existingUser);
            switch (status) {
                case -2:
                    modelMap.put("errorMessage", resources.getString("msg.invalidEmail"));
                    break;
                case -3:
                    modelMap.put("errorMessage", resources.getString("msg.invalidPassword"));
                    break;
                case -5:
                    modelMap.put("errorMessage", resources.getString("msg.invalidFirstName"));
                    break;
                case -6:
                    modelMap.put("errorMessage", resources.getString("msg.invalidLastName"));
                    break;
                case -7:
                    modelMap.put("errorMessage", resources.getString("msg.invalidLivingLocation"));
                    break;
                default:
                    modelMap.put("errorMessage", resources.getString("msg.unexpectedError"));
            }
            return "editUserForm";
        }
    }

}
