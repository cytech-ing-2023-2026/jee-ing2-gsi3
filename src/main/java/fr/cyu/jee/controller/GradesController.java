package fr.cyu.jee.controller;

import fr.cyu.jee.dto.AddGradeDTO;
import fr.cyu.jee.model.Student;
import fr.cyu.jee.model.Teacher;
import fr.cyu.jee.model.User;
import fr.cyu.jee.model.UserType;
import fr.cyu.jee.service.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/grades")
public class GradesController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SmartValidator validator;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getTeacherGrades(HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        else {
            User user = (User) session.getAttribute("user");
            return switch (user.getUserType()) {
                case ADMIN -> "admin_grades";
                case TEACHER -> "teacher_grades";
                case STUDENT -> "student_grades";
            };
        }
    }

    @RequestMapping(value = "/grades/add", method = RequestMethod.POST)
    public String addGrades(AddGradeDTO dto, HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        else {
            User user = (User) session.getAttribute("user");
            return switch (user) {
                case Teacher teacher -> {
                    System.out.println("Teacher");
                    Errors errors = validator.validateObject(dto);
                    if(errors.hasErrors()) {
                        System.out.println(errors); //TODO error message
                        yield "teacher_grades";
                    }
                    else {
                        Optional<Student> studentOpt = userRepository.findByEmailAndType(dto.getEmail(), UserType.STUDENT);
                        if (studentOpt.isPresent()) {
                            Student student = studentOpt.get();
                            student.addGrade(teacher.getSubject(), dto.getGrade());
                            System.out.println(student + ": " + student.getGrades());
                            userRepository.save(student);
                            session.setAttribute("message", student.getFirstName() + " " + student.getLastName() + " got the grade " + dto.getGrade() + " in " + teacher.getSubject());
                            yield "teacher_grades";
                        } else {
                            session.setAttribute("error", "Email not found: " + dto.getEmail());
                            yield "teacher_grades";
                        }
                    }
                }
                default -> throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            };
        }
    }
}
