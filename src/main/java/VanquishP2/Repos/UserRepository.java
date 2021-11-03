package VanquishP2.Repos;

import VanquishP2.Beans.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByID(int ID);
    Optional<User> findUserByFirstName(String username);
    void deleteByID(Integer ID);
}