package fr.cyu.jee.controller;

import fr.cyu.jee.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage(HttpSession session) {
        if(session.getAttribute("user") == null) return "redirect:/login";
        else {
            User user = (User) session.getAttribute("user");
            return switch (user.getUserType()) {
                case ADMIN -> "admin_menu";
                case TEACHER, STUDENT -> "user_menu";
            };
        }
    }
}
