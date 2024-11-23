package fr.cyu.jee;

import fr.cyu.jee.dto.RegisterDTO;
import fr.cyu.jee.model.*;
import fr.cyu.jee.service.AuthService;
import fr.cyu.jee.service.CourseRepository;
import fr.cyu.jee.service.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private AuthService authService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void run(String...args) {
        Subject java = subjectRepository.save(new Subject("Java"));
        subjectRepository.save(new Subject("Statistiques"));
        subjectRepository.save(new Subject("Architecture réseau"));
        authService.register(new RegisterDTO("Markus", "Persson", LocalDate.of(1979, 6, 1), "notch@minecraft.net", "markus"), UserType.ADMIN);
        Teacher teacher = (Teacher) authService.register(new RegisterDTO("Markus", "Persson-Teacher", LocalDate.of(1979, 6, 1), "notch_teacher@minecraft.net", "markus"), UserType.TEACHER, java).get();
        Student student = (Student) authService.register(new RegisterDTO("Markus", "Persson-Student", LocalDate.of(1979, 6, 1), "notch_student@minecraft.net", "markus"), UserType.STUDENT).get();

        courseRepository.save(new Course(LocalDateTime.of(2024, 11, 25, 8, 30), Duration.ofHours(3), java, teacher, Set.of(student)));
    }
}