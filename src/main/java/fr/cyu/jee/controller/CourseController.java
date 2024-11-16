package fr.cyu.jee.controller;

import fr.cyu.jee.model.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/course")
public class CourseController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getCoursePage(HttpSession session) {
        if (session.getAttribute("user") == null) return new ModelAndView("redirect:/login");
        else {
            return switch (session.getAttribute("user")) {
                case Admin ignored -> new ModelAndView("admin_course");
                case Teacher teacher -> {
                    Set<Course> courses = teacher.getAssignedCourses();
                    yield new ModelAndView("user_course", Map.of("courses", courses));
                }
                case Student student -> {
                    Set<Course> courses = student.getCourses();
                    yield new ModelAndView("user_course", Map.of("courses", courses));
                }
                default -> throw new AssertionError("Unexpected user");
            };
        }
    }
}
