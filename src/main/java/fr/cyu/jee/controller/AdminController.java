package fr.cyu.jee.controller;

import fr.cyu.jee.dto.AddCourseDTO;
import fr.cyu.jee.dto.DeleteCourseDTO;
import fr.cyu.jee.dto.UpdateCourseDTO;
import fr.cyu.jee.model.Course;
import fr.cyu.jee.model.Student;
import fr.cyu.jee.model.User;
import fr.cyu.jee.service.CourseRepository;
import fr.cyu.jee.service.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

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

    @RequestMapping(value = "/course/delete", method = RequestMethod.POST)
    public String deleteCourse(@Validated DeleteCourseDTO dto) {
        courseRepository.deleteById(dto.getId());
        return "redirect:/course";
    }

    @RequestMapping(value = "/course/add", method = RequestMethod.POST)
    public ModelAndView addCourse(@Validated AddCourseDTO dto) {
        courseRepository.save(new Course(dto.getBeginDate(), dto.getDuration(), dto.getSubject(), dto.getTeacher(), new HashSet<>(dto.getStudents())));
        return new ModelAndView("redirect:/course", Map.of("message", "Successfully registered course"));
    }

    @RequestMapping(value = "/course/update", method = RequestMethod.POST)
    public ModelAndView updateCourse(@Validated UpdateCourseDTO dto) {
        Course course = dto.getCourse();
        course.setBeginDate(dto.getBeginDate());
        course.setDuration(dto.getDuration());
        course.setSubject(dto.getSubject());
        course.setTeacher(dto.getTeacher());
        course.setStudents(new HashSet<>(dto.getStudents()));
        courseRepository.save(course);
        return new ModelAndView("redirect:/course", Map.of("message", "Successfully updated course"));
    }
}
