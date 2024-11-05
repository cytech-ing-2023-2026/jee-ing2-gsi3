package fr.cyu.jee.controller;

import fr.cyu.jee.dto.LoginDTO;
import fr.cyu.jee.dto.RegisterDTO;
import fr.cyu.jee.model.User;
import fr.cyu.jee.model.UserType;
import fr.cyu.jee.service.AuthService;
import fr.cyu.jee.service.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String getRegisterPage(){
        return "register";
    }
}
