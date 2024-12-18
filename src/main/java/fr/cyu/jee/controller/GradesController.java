package fr.cyu.jee.controller;

import fr.cyu.jee.dto.AddGradeDTO;
import fr.cyu.jee.dto.DeleteGradeDTO;
import fr.cyu.jee.dto.UpdateGradeDTO;
import fr.cyu.jee.model.*;
import fr.cyu.jee.service.GradeRepository;
import fr.cyu.jee.service.SubjectRepository;
import fr.cyu.jee.service.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/grades")
public class GradesController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getGrades(HttpSession session, @RequestParam(required = false) String error, @RequestParam(required = false) String message) {
        return switch ((User) session.getAttribute("user")) {
            case Admin ignored -> new ModelAndView("teacher_grades", Map.ofEntries(
                    Map.entry("grades", gradeRepository.getAllOrdered()),
                    Map.entry("subjects", subjectRepository.findAll()),
                    Map.entry("error", error == null ? "" : error),
                    Map.entry("message", message == null ? "" : message)
            ));
            case Teacher teacher -> new ModelAndView("teacher_grades", Map.ofEntries(
                    Map.entry("grades", gradeRepository.getAllBySubjectOrdered(teacher.getSubject().getId())),
                    Map.entry("error", error == null ? "" : error),
                    Map.entry("message", message == null ? "" : message)
            ));
            case Student student -> new ModelAndView("student_grades", Map.ofEntries(
                    Map.entry("grades", gradeRepository.getAllByStudentOrdered(student.getId())),
                    Map.entry("error", error == null ? "" : error),
                    Map.entry("message", message == null ? "" : message)
            ));
            default -> throw new AssertionError("Invalid user type ");
        };
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addGrades(@Validated AddGradeDTO dto, HttpSession session) {
        User user = (User) session.getAttribute("user");

        Optional<Student> studentOpt = userRepository.findByEmailAndType(dto.getEmail(), UserType.STUDENT);
        if (studentOpt.isEmpty()) return new ModelAndView("redirect:/grades", Map.of("error", "Email not found: " + dto.getEmail()));
        Student student = studentOpt.get();

        return switch (user) {

            case Admin ignored -> {
                if(dto.getSubject().isPresent()) {
                    student.addGrade(dto.getSubject().get(), dto.getGrade());
                    userRepository.save(student);
                    yield new ModelAndView("redirect:/grades", Map.of("message", student.getFirstName() + " " + student.getLastName() + " got the grade " + dto.getGrade() + " in " + dto.getSubject().get().getName()));
                } else {
                    yield new ModelAndView("redirect:/grades", Map.of("error", "You must define a subject for this grade"));
                }
            }

            case Teacher teacher -> {
                if(dto.getSubject().isPresent()) {
                    yield new ModelAndView("redirect:/grades", Map.of("error", "You cannot specify a subject as teacher"));
                } else {
                    student.addGrade(teacher.getSubject(), dto.getGrade());
                    userRepository.save(student);
                    yield new ModelAndView("redirect:/grades", Map.of("message", student.getFirstName() + " " + student.getLastName() + " got the grade " + dto.getGrade() + " in " + teacher.getSubject().getName()));
                }
            }
            default -> throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        };
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteGrades(@Validated DeleteGradeDTO dto, HttpSession session) {
        System.out.println("DELETE " + dto.getGrade().getId());
        return switch ((User) session.getAttribute("user")) {
            case Admin ignored -> {
                gradeRepository.delete(dto.getGrade());
                yield new ModelAndView("redirect:/grades");
            }
            case Teacher teacher -> {
                if(teacher.getSubject().getId() == dto.getGrade().getSubject().getId()) {
                    gradeRepository.delete(dto.getGrade());
                    yield new ModelAndView("redirect:/grades");
                } else {
                    yield new ModelAndView("redirect:/grades", Map.of("error", "This grade is not associated with your teaching subject"));
                }
            }

            default -> throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        };
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateGrades(@Validated UpdateGradeDTO dto, HttpSession session) {
        return switch ((User) session.getAttribute("user")) {
            case Admin ignored -> {
                Grade grade = dto.getGrade();
                grade.setValue(dto.getValue());
                dto.getSubject().ifPresent(grade::setSubject);
                gradeRepository.save(grade);
                yield new ModelAndView("redirect:/grades");
            }

            case Teacher teacher -> {
                if(teacher.getSubject().getId() != dto.getGrade().getSubject().getId())
                    yield new ModelAndView("redirect:/grades", Map.of("error", "This grade is not associated with your teaching subject"));
                else if(dto.getSubject().isPresent())
                    yield new ModelAndView("redirect:/grades", Map.of("error", "Only admins can change the subject of a grade"));
                else {
                    Grade grade = dto.getGrade();
                    grade.setValue(dto.getValue());
                    gradeRepository.save(grade);
                    yield new ModelAndView("redirect:/grades");
                }
            }

            default -> throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        };
    }
}