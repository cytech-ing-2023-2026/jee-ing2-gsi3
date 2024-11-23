package fr.cyu.jee.service;

import fr.cyu.jee.model.User;
import fr.cyu.jee.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(int id);
    Optional<User> findByEmailAndPassword(String username, String password);
    default <T extends User> Optional<T> findByEmailAndType(String email, UserType type) {
        return (Optional<T>) findByEmail(email).filter(user -> user.getUserType() == type);
    }

    @Query(
            value = """
                    SELECT * FROM users
                    WHERE dtype = :dtype
                    ORDER BY email ASC
                    """,
            nativeQuery = true)
    List<User> findAllByDtypeOrdered(@Param("dtype") String dtype);

    default List<User> findAllByTypeOrdered(UserType type) {
        return findAllByDtypeOrdered(type.getDtype());
    }

    List<User> findAllByOrderByIdAsc();
}
