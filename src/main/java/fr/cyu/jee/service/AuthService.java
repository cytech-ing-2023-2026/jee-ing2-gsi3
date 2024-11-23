package fr.cyu.jee.service;

import fr.cyu.jee.dto.LoginDTO;
import fr.cyu.jee.dto.RegisterDTO;
import fr.cyu.jee.model.User;
import fr.cyu.jee.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> authenticate(LoginDTO loginDTO) {
        return userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
    }

    public Optional<User> register(RegisterDTO registerDTO) {
        if(userRepository.findByEmail(registerDTO.getEmail()).isPresent()) return Optional.empty();
        else {
            UserType userType = registerDTO.getUserType();
            return Optional.of(userRepository.save(userType.createUser(registerDTO.getEmail(), registerDTO.getPassword(), registerDTO.getFirstName(), registerDTO.getLastName(), registerDTO.getDob(), registerDTO.getSubject().orElse(null))));
        }
    }
}
