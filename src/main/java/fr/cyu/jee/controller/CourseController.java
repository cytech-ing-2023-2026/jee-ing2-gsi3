package fr.cyu.jee.controller;

import fr.cyu.jee.model.*;
import fr.cyu.jee.service.CourseRepository;
import fr.cyu.jee.service.SubjectRepository;
import fr.cyu.jee.service.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getCoursePage(HttpSession session, Optional<LocalDate> date, @RequestParam(required = false) String error, @RequestParam(required = false) String message) {
        // Récupère la date sélectionnée dans les paramètres de requête
        LocalDate selectedDate = date.orElse(LocalDate.now()); // Utilise la date du jour si aucun paramètre n'est fourni
        LocalDate selectedMonday = selectedDate.minusDays(selectedDate.getDayOfWeek().ordinal());
        LocalDate selectedSunday = selectedMonday.plusDays(7);

        return switch (session.getAttribute("user")) {
            case Admin ignored -> {
                Iterable<Course> coursesIt = courseRepository.findAll();
                List<Course> courses = new ArrayList<>();
                coursesIt.forEach(courses::add);

                Iterable<Subject> subjectsIt = subjectRepository.findAll();
                List<Subject> subjects = new ArrayList<>();
                subjectsIt.forEach(subjects::add);

                yield new ModelAndView("admin_course", Map.ofEntries(
                        Map.entry("courses", courses),
                        Map.entry("subjects", subjects),
                        Map.entry("students", userRepository.findAllByTypeOrdered(UserType.STUDENT)),
                        Map.entry("teachers", userRepository.findAllByTypeOrdered(UserType.TEACHER)),
                        Map.entry("error", error == null ? "" : error),
                        Map.entry("message", message == null ? "" : message)
                ));
            }
            case Teacher teacher -> {
                Set<Course> courses = courseRepository.getTeacherCourses(teacher.getId(), selectedMonday, selectedSunday);
                yield new ModelAndView("user_course", Map.ofEntries(
                        Map.entry("courses", courses),
                        Map.entry("selectedMonday", selectedMonday),
                        Map.entry("selectedSunday", selectedSunday),
                        Map.entry("error", error == null ? "" : error),
                        Map.entry("message", message == null ? "" : message)
                ));
            }
            case Student student -> {
                Set<Course> courses = courseRepository.getStudentCourses(student.getId(), selectedMonday, selectedSunday);
                yield new ModelAndView("user_course", Map.ofEntries(
                        Map.entry("courses", courses),
                        Map.entry("selectedMonday", selectedMonday),
                        Map.entry("selectedSunday", selectedSunday),
                        Map.entry("error", error == null ? "" : error),
                        Map.entry("message", message == null ? "" : message)
                ));
            }
            default -> throw new AssertionError("Unexpected user");
        };
    }
}
