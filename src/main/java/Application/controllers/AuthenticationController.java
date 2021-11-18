package Application.controllers;

import Application.DTOs.LoginCredentialsDTO;
import Application.DTOs.UserRegistrationDTO;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * AuthenticationController
 * Handles requests that involve logging in and registering User
 *
 * @date 11/3/2021
 * @author Kollier Martin
 */
@RestController
@RequestMapping("/4TheMusic")
public class AuthenticationController {
    private final UserInfoService userInfoService;
    private final UserService userService;

    @Autowired
    public AuthenticationController(UserInfoService userInfoService, UserService userService) {
        this.userInfoService = userInfoService;
        this.userService = userService;
    }

    /**
     * Authenticate and Login User
     * @param credentials username and password
     * @return The new user and their key
     */
    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserInfo authenticate(@RequestBody LoginCredentialsDTO credentials) {
        return userInfoService.authenticate(credentials.getUsername(), credentials.getPassword()).get();
    }

    /**
     * Register Basic User
     * @param regData Registration Data from Frontend
     * @return The new registered User Data
     */
    @PostMapping(value = "user/register/basic", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User registerBasicUser(@RequestBody @Valid UserRegistrationDTO regData){
        return userService.registerUser(regData, User.Role.BASIC);
    }

    /**
     * Register Premium User
     * @param regData Registration Data from Frontend
     * @return The new registered User Data
     */
    @PostMapping(value = "user/register/premium", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User registerPremiumUser(@RequestBody @Valid UserRegistrationDTO regData){
        return userService.registerUser(regData, User.Role.PREMIUM);
    }
}
