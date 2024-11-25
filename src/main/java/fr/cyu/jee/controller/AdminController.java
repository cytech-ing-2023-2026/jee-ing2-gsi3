package fr.cyu.jee.controller;

import fr.cyu.jee.dto.*;
import fr.cyu.jee.model.*;
import fr.cyu.jee.service.AuthService;
import fr.cyu.jee.service.CourseRepository;
import fr.cyu.jee.service.SubjectRepository;
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
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getHomePage(@RequestParam(required = false) String error, @RequestParam(required = false) String message) {
        return new ModelAndView("admin_users_menu", Map.ofEntries(
            Map.entry("error", error == null ? "" : error),
            Map.entry("message", message == null ? "" : message)
        ));
    }

    @RequestMapping(value = "/add_users", method = RequestMethod.GET)
    public ModelAndView getAddUserPage(@RequestParam(required = false) String error, @RequestParam(required = false) String message) {
        Iterable<Subject> subjectsIt = subjectRepository.findAll();
        List<Subject> subjects = new ArrayList<>();
        subjectsIt.forEach(subjects::add);

        return new ModelAndView("admin_add_users", Map.ofEntries(
            Map.entry("error", error == null ? "" : error),
            Map.entry("message", message == null ? "" : message),
            Map.entry("subjects", subjects)
        ));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(RegisterDTO registerDTO) {
        if(registerDTO.getUserType() == UserType.TEACHER && registerDTO.getSubject().isEmpty())
            return new ModelAndView("redirect:/admin/add_users", Map.of("error", "A teacher must have an assigned subject"));
        Optional<User> registered = authService.register(registerDTO);
        if(registered.isPresent()) return new ModelAndView("redirect:/admin/add_users", Map.of("message", "Successfully registered " + registered.get().getEmail()));
        else return new ModelAndView("redirect:/admin/add_users", Map.of("error", "Email already taken"));
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView getDisplayPage(SearchDTO dto, @RequestParam(required = false) String error, @RequestParam(required = false) String message) {

        return new ModelAndView("admin_display_users", Map.ofEntries(
                Map.entry("users", userRepository
                        .findAllByOrderByIdAsc()
                        .stream()
                        .filter(dto::check)
                        .toList()
                ),
                Map.entry("error", error == null ? "" : error),
                Map.entry("message", message == null ? "" : message)
        ));
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView getDisplayWithSearchPage(SearchDTO dto, @RequestParam(required = false) String error, @RequestParam(required = false) String message){
        return new ModelAndView("admin_display_users", Map.ofEntries(
                Map.entry("users", userRepository
                        .findAllByOrderByIdAsc()
                        .stream()
                        .filter(dto::check)
                        .toList()
                ),
                Map.entry("error", error == null ? "" : error),
                Map.entry("message", message == null ? "" : message)
        ));
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
        return new ModelAndView("redirect:/admin/display", Map.of("message", "Successfully deleted user"));
    }

    @RequestMapping(value = "/displayModify", method = RequestMethod.POST)
    public ModelAndView displayModifyUser(@RequestParam("userId") int userId) {
        // Fetch user by ID
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            Iterable<Subject> subjectsIt = subjectRepository.findAll();
            List<Subject> subjects = new ArrayList<>();
            subjectsIt.forEach(subjects::add);
            return new ModelAndView("admin_modify_users", Map.of("user", user.get(), "users", userRepository.findAllByOrderByIdAsc(), "subjects", subjects));
        }
        else return new ModelAndView("redirect:/admin/display", Map.of("error", "Unknown user with id " + userId, "users", userRepository.findAllByOrderByIdAsc() ));
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ModelAndView modifyUser(@RequestParam("userId") int userId,
                                   @RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("dob") LocalDate dob,
                                   @RequestParam("password") String password,
                                   @RequestParam("email") String email,
                                   @RequestParam(value = "subject", required = false) Subject subject) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User currentUser = user.get();

            if(currentUser.getUserType() == UserType.TEACHER && subject == null)
                return new ModelAndView("redirect:/admin/display", Map.of("error", "Teacher must have a subject"));

            currentUser.setFirstName(firstName);
            currentUser.setLastName(lastName);

            if(!email.equals(currentUser.getEmail()) && userRepository.findByEmail(email).isPresent())
                return new ModelAndView("redirect:/admin/display", Map.of("error", "This email is already taken"));
            currentUser.setEmail(email);
            currentUser.setPassword(password);
            currentUser.setDob(dob);
            if(currentUser.getUserType() == UserType.TEACHER) ((Teacher) currentUser).setSubject(subject);

            userRepository.save(currentUser);

            return new ModelAndView("redirect:/admin/display", Map.of("message", "Successfully updated user"));
        } else {
            return new ModelAndView("redirect:/admin/display", Map.of("error", "User with ID " + userId + " does not exist."));
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
