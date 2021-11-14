package Models;

import VanquishP2.Application.Beans.Models.Location;
import VanquishP2.Application.Beans.Models.User;
import VanquishP2.Application.Beans.Models.UserInfo;
import VanquishP2.Application.Beans.Repos.UserInfoRepository;
import VanquishP2.Application.Beans.Repos.UserRepository;
import VanquishP2.Application.P2Application;
import VanquishP2.DTOs.UserRegistrationDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = P2Application.class)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class UserDBTest {
    @Resource
    private UserRepository userRepository;

    @Resource
    private UserInfoRepository userInfoRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void goodSave(){
        UserInfo userInfo = new UserInfo(new UserRegistrationDTO("Kollier",
                "Martin",
                "KMART23",
                "3t3styG=5!",
                "astronomical@space.net",
                "Mars",
                "Milky Way"));

        userInfoRepository.save(userInfo);

        User user = new User(User.Role.PREMIUM, userInfo);
        userRepository.save(user);

        Assertions.assertEquals("Koller", userInfoRepository.findByFirstName("Kollier").get().getFirstName());
    }
}
