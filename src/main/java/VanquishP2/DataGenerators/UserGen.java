package VanquishP2.DataGenerators;

import VanquishP2.Beans.Models.User;
import VanquishP2.Beans.Models.UserInfo;
import VanquishP2.DTOs.UserRegistrationDTO;
import VanquishP2.Repos.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

import static VanquishP2.DataGenerators.DataLists.*;

@Component
public class UserGen {
    private static Random rand = new Random();
    private static int index;
    private UserRepository userRepository;
    private UserRegistrationDTO userRegistrationDTO;

    @PostConstruct
    private void populate() {
        userRegistrationDTO = new UserRegistrationDTO(getAUsername(), getAPassword(), getAEmail());
        User user = new User(userRegistrationDTO);
        UserInfo userInfo;
        userRepository.save(user);
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
