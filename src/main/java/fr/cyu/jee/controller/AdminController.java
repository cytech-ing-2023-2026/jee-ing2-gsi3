package fr.cyu.jee.controller;

import fr.cyu.jee.dto.AddCourseDTO;
import fr.cyu.jee.dto.DeleteCourseDTO;
import fr.cyu.jee.dto.RegisterDTO;
import fr.cyu.jee.dto.UpdateCourseDTO;
import fr.cyu.jee.model.Course;
import fr.cyu.jee.model.Student;
import fr.cyu.jee.model.User;
import fr.cyu.jee.service.AuthService;
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

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getHomePage() {
        return "admin_users_menu";
    }

    @RequestMapping(value = "/add_users", method = RequestMethod.GET)
    public String getAddUserPage() {
        return "admin_add_users";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(RegisterDTO registerDTO) {
        Optional<User> registered = authService.register(registerDTO);
        if(registered.isPresent()) return new ModelAndView("redirect:/admin/add_users", Map.of("message", "Successfully registered " + registered.get().getEmail()));
        else return new ModelAndView("redirect:/admin/add_users", Map.of("error", "Email already taken"));
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView getDisplayPage() {
        return new ModelAndView("admin_display_users", Map.of("users", userRepository.findAllByOrderByIdAsc()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ModelAndView removeUser(@RequestParam("userId") User user, HttpSession session) {
        if(user.getId() == ((User) session.getAttribute("user")).getId()) return new ModelAndView("redirect:/admin/display", Map.of("error", "Cannot delete yourself"));

        if(user instanceof Student student) {
            Set<Course> courses = student.getCourses();
            courses.forEach(course -> course.getStudents().remove(student));
            courseRepository.saveAll(courses);
        }

        userRepository.delete(user);
        return new ModelAndView("redirect:/admin/display", Map.of("users", userRepository.findAllByOrderByIdAsc()));
    }

    @RequestMapping(value = "/displayModify", method = RequestMethod.POST)
    public ModelAndView displayModifyUser(@RequestParam("userId") int userId) {
        // Fetch user by ID
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) return new ModelAndView("admin_modify_users", Map.of("user", user.get(), "users", userRepository.findAllByOrderByIdAsc()));
        else return new ModelAndView("redirect:/admin/display", Map.of("error", "Unknown user with id " + userId, "users", userRepository.findAllByOrderByIdAsc() ));
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
        }
    }

    @RequestMapping(value = "/course/delete", method = RequestMethod.POST)
    public String deleteCourse(@Validated DeleteCourseDTO dto) {
        courseRepository.delete(dto.getCourse());
        return "redirect:/course";
    }

    @RequestMapping(value = "/course/add", method = RequestMethod.POST)
    public ModelAndView addCourse(@Validated AddCourseDTO dto) {
        Optional<Duration> duration = dto.getDuration();
        if(duration.isEmpty()) return new ModelAndView("redirect:/course", Map.of("error", "Invalid duration"));
        courseRepository.save(new Course(dto.getBeginDate(), duration.get(), dto.getSubject(), dto.getTeacher(), new HashSet<>(dto.getStudents())));
        return new ModelAndView("redirect:/course", Map.of("message", "Successfully registered course"));
    }

    @RequestMapping(value = "/course/update", method = RequestMethod.POST)
    public ModelAndView updateCourse(@Validated UpdateCourseDTO dto) {
        Optional<Duration> duration = dto.getDuration();
        if(duration.isEmpty()) return new ModelAndView("redirect:/course", Map.of("error", "Invalid duration"));
        Course course = dto.getCourse();
        course.setBeginDate(dto.getBeginDate());
        course.setDuration(duration.get());
        course.setSubject(dto.getSubject());
        course.setTeacher(dto.getTeacher());
        course.setStudents(new HashSet<>(dto.getStudents()));
        courseRepository.save(course);
        return new ModelAndView("redirect:/course", Map.of("message", "Successfully updated course"));
    }
}
