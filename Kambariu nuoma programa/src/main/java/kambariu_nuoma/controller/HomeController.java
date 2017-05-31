package kambariu_nuoma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping(value="/")
    public static String indexHome (HttpSession session, ModelMap modelMap){

        modelMap.put("username", session.getAttribute("username"));
        return "index";

    }
    @RequestMapping("/index")
    public static String index (HttpSession session, ModelMap modelMap) {
        return indexHome(session, modelMap);
    }

}