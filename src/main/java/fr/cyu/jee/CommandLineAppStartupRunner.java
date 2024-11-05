package fr.cyu.jee;

import fr.cyu.jee.dto.RegisterDTO;
import fr.cyu.jee.model.UserType;
import fr.cyu.jee.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    AuthService authService;

    @Override
    public void run(String...args) {
        authService.register(new RegisterDTO("Markus", "Persson", LocalDate.of(1979, 6, 1), "notch@minecraft.net", "markus"), UserType.ADMIN);
    }
}