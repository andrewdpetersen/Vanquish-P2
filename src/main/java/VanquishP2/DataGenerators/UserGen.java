package VanquishP2.DataGenerators;

import VanquishP2.Application.Beans.ModelServices.UserService;
import VanquishP2.Application.Beans.Models.User;
import VanquishP2.Application.Beans.Models.UserInfo;
import VanquishP2.DTOs.UserRegistrationDTO;
import VanquishP2.Application.Beans.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;

import static VanquishP2.DataGenerators.DataLists.*;

//@Service
public class UserGen {
    private static Random rand = new Random();
    private static int index;
    private final UserService userService;
    private UserRegistrationDTO userRegistrationDTO;

    //@Autowired
    public UserGen(UserService userService) {
        this.userService = userService;
    }

    //@PostConstruct
    private void populate() {
        userRegistrationDTO = new UserRegistrationDTO(getAFirstName(), getALastName(),
                getAUsername(), getAPassword(), getAEmail());
        UserInfo userInfo = new UserInfo(userRegistrationDTO);
        User user = new User(User.Role.BASIC, userInfo);
        userService.save(user);
    }

    public static String getAFirstName() {
        index = rand.nextInt(names.length);
        return names[index];
    }

    public static String getALastName() {
        index = rand.nextInt(names.length);
        return names[index];
    }

    public static String getAPassword() {
        index = rand.nextInt(passwords.length);
        return passwords[index];
    }

    public static String getAUsername() {
        index = rand.nextInt(usernames.length);
        return usernames[index];
    }

    public static String getAEmail() {
        index = rand.nextInt(emails.length);
        return emails[index];
    }
}
