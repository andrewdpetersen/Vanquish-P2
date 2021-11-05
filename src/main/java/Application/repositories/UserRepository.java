package Application.repositories;

import Application.models.User;
import Application.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByID(int ID);
    Optional<User> findUserByUserInfo(UserInfo userInfo);
    void deleteByID(Integer ID);
}