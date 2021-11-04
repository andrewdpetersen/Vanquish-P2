package VanquishP2.Application.Beans.Repos;

import VanquishP2.Application.Beans.Models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findUserByUsernameAndPassword(String username, String password);
    Optional<UserInfo> findByUsername(String username);
}