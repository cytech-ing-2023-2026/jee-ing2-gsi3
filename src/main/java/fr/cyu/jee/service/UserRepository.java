package fr.cyu.jee.service;

import fr.cyu.jee.model.User;
import fr.cyu.jee.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    default <T extends User> Optional<T> findByEmailAndType(String email, UserType type) {
        return (Optional<T>) findByEmail(email).filter(user -> user.getUserType() == type);
    }

    Optional<User> findByEmailAndPassword(String username, String password);
}
