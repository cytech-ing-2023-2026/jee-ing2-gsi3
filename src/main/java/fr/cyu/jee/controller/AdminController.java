package fr.cyu.jee.controller;

import fr.cyu.jee.dto.RegisterDTO;
import fr.cyu.jee.dto.AddCourseDTO;
import fr.cyu.jee.dto.DeleteCourseDTO;
import fr.cyu.jee.dto.UpdateCourseDTO;
import fr.cyu.jee.model.Course;
import fr.cyu.jee.model.Student;
import fr.cyu.jee.model.User;
import fr.cyu.jee.service.AuthService;
import fr.cyu.jee.service.UserRepository;
import fr.cyu.jee.service.UserService;
import fr.cyu.jee.service.CourseRepository;
import fr.cyu.jee.service.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getHomePage(HttpSession session) {
        return "admin_users_menu";
    }

    @RequestMapping(value = "/add_users", method = RequestMethod.GET)
    public String getAddUserPage(HttpSession session) {
        return "admin_add_users";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(RegisterDTO registerDTO, HttpSession session, Model model) {
        Optional<User> registered = authService.register(registerDTO);
        if(registered.isPresent()){
            session.setAttribute("user", registered.get());
            return "admin_add_users";
        } else {
            model.addAttribute("error", "Email is already taken");
            return "admin_add_users";
        }
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView getDisplayPage() {
        return new ModelAndView("admin_display_users", Map.of("users", userRepository.findAllByOrderByIdAsc()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ModelAndView removeUser(@RequestParam("userId") int userId) {
        userRepository.deleteById(userId);
        return new ModelAndView("redirect:/display", Map.of("users", userRepository.findAllByOrderByIdAsc()));
    }

    @RequestMapping(value = "/displayModify", method = RequestMethod.POST)
    public ModelAndView displayModifyUser(@RequestParam("userId") int userId) {
        // Fetch user by ID
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) return new ModelAndView("admin_display_users", Map.of("user", user.get(), "users", userRepository.findAllByOrderByIdAsc()));
        else return new ModelAndView("redirect:/display", Map.of("error", "Unknown user with id " + userId));
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ModelAndView modifyUser(@RequestParam("userId") int userId,
                                   @RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("dob") LocalDate dob,
                                   @RequestParam("password") String password,
                                   @RequestParam("email") String email) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User currentUser = user.get();

            currentUser.setFirstName(firstName);
            currentUser.setLastName(lastName);
            currentUser.setEmail(email);
            currentUser.setPassword(password);
            currentUser.setDob(dob);

            userRepository.save(currentUser);

            return new ModelAndView("admin_display_users", Map.of("users", userRepository.findAllByOrderByIdAsc()));
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }

        userService.updateUser(currentUser);

        return new ModelAndView("admin_display_users", Map.of("users", userRepository.findAllByOrderByIdAsc()));
    }

    @RequestMapping(value = "/grades", method = RequestMethod.GET)
    public String getGradesPage(HttpSession session) {
        if(session.getAttribute("user") == null) return "redirect:/login";
        else {
            return "admin_grades";

        }
    }

    @RequestMapping(value = "/planning", method = RequestMethod.GET)
    public String getPlanningPage(HttpSession session) {
        if(session.getAttribute("user") == null) return "redirect:/login";
        else {
            return "admin_planning";

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
