package VanquishP2.DataGenerators;

import VanquishP2.Application.Beans.ModelServices.LocationService;
import VanquishP2.Application.Beans.ModelServices.UserInfoService;
import VanquishP2.Application.Beans.ModelServices.UserService;
import VanquishP2.Application.Beans.Models.Location;
import VanquishP2.Application.Beans.Models.User;
import VanquishP2.Application.Beans.Models.UserInfo;
import VanquishP2.DTOs.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Random;
import static VanquishP2.DataGenerators.DataLists.*;

@Service
public class UserGen {
    private static Random rand = new Random();
    private static int index;
    private final UserService userService;
    private final UserInfoService userInfoService;
    private final LocationService locationService;

    @Autowired
    public UserGen(UserService userService, UserInfoService userInfoService, LocationService locationService) {
        this.locationService = locationService;
        this.userInfoService = userInfoService;
        this.userService = userService;
    }

    @PostConstruct
    private void populate() {
        for (int i = 0; i < 20; i++) {
            UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(getAFirstName(), getALastName(),
                    getAUsername(), getAPassword(), getAEmail(), getALocation());

            UserInfo userInfo = new UserInfo(userRegistrationDTO);
            locationService.save(userRegistrationDTO.getLocation());
            userInfoService.save(userInfo);

            User user = new User(User.Role.BASIC, userInfo);
            userService.save(user);

            userInfo = userInfoService.getByFirstName(userInfo.getFirstName());
            userInfo.setUser(user);
            userInfoService.save(userInfo);
        }
    }

    public static Location getALocation() {
        Random rand = new Random();
        int index = rand.nextInt(cities.length);
        return new Location(cities[index], states[index]);
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
