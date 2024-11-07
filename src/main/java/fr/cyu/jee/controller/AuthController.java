package fr.cyu.jee.controller;

import fr.cyu.jee.dto.LoginDTO;
import fr.cyu.jee.dto.RegisterDTO;
import fr.cyu.jee.model.User;
import fr.cyu.jee.model.UserType;
import fr.cyu.jee.service.AuthService;
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

    @RequestMapping(value="/forgotten_password", method = RequestMethod.GET)
    public String getForgottenPasswordPage(){
        return "forgotten_password";
    }

    @RequestMapping(value="/teacher_menu", method = RequestMethod.GET)
    public String getTeacherMenuPage(){
        return "teacher_menu";
    }

    @RequestMapping(value="/grades_teacher", method = RequestMethod.GET)
    public String getTeacherGradesPage(){
        return "grades_teacher";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(LoginDTO loginDTO, HttpSession session, Model model) {
        Optional<User> loggedIn = authService.authenticate(loginDTO);
        if(loggedIn.isPresent()){
            session.setAttribute("user", loggedIn.get());
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(RegisterDTO registerDTO, HttpSession session, Model model) {
        Optional<User> registered = authService.register(registerDTO, UserType.STUDENT);
        if(registered.isPresent()){
            session.setAttribute("user", registered.get());
            return "redirect:/";
        } else {
            model.addAttribute("error", "Email is already taken");
            return "register";
        }
    }
}
