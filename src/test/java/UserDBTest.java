import VanquishP2.Application.Beans.Models.Location;
import VanquishP2.Application.Beans.Models.User;
import VanquishP2.Application.Beans.Models.UserInfo;
import VanquishP2.Application.Beans.Repos.LocationRepository;
import VanquishP2.Application.Beans.Repos.UserInfoRepository;
import VanquishP2.Application.Beans.Repos.UserRepository;
import VanquishP2.Application.P2Application;
import VanquishP2.DTOs.UserRegistrationDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = P2Application.class)
class UserDBTest {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void userInfoRegisterTest(){
        UserRegistrationDTO registeredUser = new UserRegistrationDTO("Kollier",
                "Martin",
                "KMART23",
                "3t3styG=5!",
                "astronomical@space.net",
                "Mars",
                "Milky Way");

        UserInfo userInfo = new UserInfo(registeredUser);
        User newUser = new User();

        locationRepository.save(userInfo.getLocation());
        userInfo = userInfoRepository.save(userInfo);
        newUser = userRepository.save(newUser);

        userInfo.setUser(newUser);

        Assertions.assertNotNull(userInfoRepository.save(userInfo));
    }

    @Test
    public void testSaveThenDelete(){
        User newUser = new User();
        newUser = userRepository.save(newUser);
        userRepository.delete(newUser);

        Assertions.assertEquals(Optional.empty(), userRepository.findByID(newUser.getID()));
    }
}




