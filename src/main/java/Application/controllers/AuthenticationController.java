package Application.controllers;

import Application.DTOs.LoginCredentialsDTO;
import Application.DTOs.UserRegistrationDTO;
import Application.exceptions.UserDoesNotExistException;
import Application.models.User;
import Application.models.UserInfo;
import Application.services.UserInfoService;
import Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 *
 */
@RestController
@RequestMapping("/4TheMusic")
public class AuthenticationController {
    private final UserInfoService userInfoService;
    private final UserService userService;

    /**
     * Authenticate and Login User
     * @param credentials username and password
     * @return The new user and their key
     */
    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserInfo authenticate(@RequestBody LoginCredentialsDTO credentials) {
        Optional<UserInfo> userInfo = userInfoService.authenticate(credentials.getUsername(), credentials.getPassword());

        if (!userInfo.isPresent()){
            throw new UserDoesNotExistException("User does not exist!");
        }

        return userInfo.get();
    }

    @Autowired
    public AuthenticationController(UserInfoService userInfoService, UserService userService) {
        this.userInfoService = userInfoService;
        this.userService = userService;
    }

    /**
     * Register Basic User
     * @param regData Registration Data from Frontend
     * @return The new registered User Data
     */
    @PostMapping(value = "/register/basic", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User registerBasicUser(@RequestBody @Valid UserRegistrationDTO regData){
        return userService.registerUser(regData, User.Role.BASIC);
    }

    /**
     * Register Premium User
     * @param regData Registration Data from Frontend
     * @return The new registered User Data
     */
    @PostMapping(value = "/register/premium", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User registerPremiumUser(@RequestBody @Valid UserRegistrationDTO regData){
        return userService.registerUser(regData, User.Role.PREMIUM);
    }
}
