package VanquishP2.Application.Beans.Repos;

import VanquishP2.Application.Beans.Models.User;
import VanquishP2.Application.Beans.Models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByID(int ID);
    Optional<User> findUserByUserInfo(UserInfo userInfo);
    void deleteByID(Integer ID);
}