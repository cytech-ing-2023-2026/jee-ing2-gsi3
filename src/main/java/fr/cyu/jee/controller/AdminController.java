package fr.cyu.jee.controller;

import fr.cyu.jee.dto.DeleteCourseDTO;
import fr.cyu.jee.model.User;
import fr.cyu.jee.service.CourseRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getHomePage(HttpSession session) {
        if(session.getAttribute("user") == null) return "redirect:/login";
        else {

            User user = (User) session.getAttribute("user");
            return switch (user.getUserType()) {
                case ADMIN -> "admin_users_menu";
                case TEACHER, STUDENT -> "redirect:/home";

            };
        }
    }

    @RequestMapping("/course/delete")
    public String deleteCourse(@Validated DeleteCourseDTO dto) {
        courseRepository.deleteById(dto.getId());
        return "redirect:/course";
    }
}
