package fr.cyu.jee;

import fr.cyu.jee.model.Admin;
import fr.cyu.jee.model.User;
import fr.cyu.jee.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String...args) {
        System.out.println("Registering admin...");
        if(userRepository.findByEmail("notch@minecraft.net") == null) {
            User admin = new Admin("notch@minecraft.net", "markus", "Markus", "Persson", LocalDate.of(1979, 6, 1));
            userRepository.save(admin);
            System.out.println("Admin hash: " + userRepository.findByEmail("notch@minecraft.net").getPassword());
        }
    }
}