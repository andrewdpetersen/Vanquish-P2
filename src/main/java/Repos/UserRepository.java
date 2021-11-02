package Repos;

import Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getAllByID();
    Optional<User> findByID(int ID);
    Optional<User> findUserByFirstNameAndPassword(String firstName, String password);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByFirstName(String username);
}