package Application.controllers;

import Application.DTOs.LoginCredentialsDTO;
import Application.DTOs.UserRegistrationDTO;
import Application.models.User;
import Application.models.UserInfo;
import Application.services.UserInfoService;
import Application.services.UserService;
import Application.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 *
 */
@RestController
@RequestMapping("/4TheMusic")
public class AuthenticationController {

    private final JWTUtil jwtUtil;
    private final UserInfoService userInfoService;
    private final UserService userService;

    @Autowired
    public AuthenticationController(JWTUtil jwtUtil, UserInfoService userInfoService, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userInfoService = userInfoService;
        this.userService = userService;
    }

    /**
     * Authenticate and Login User
     * @param credentials username and password
     * @param response The HTTP Response
     * @return The new user and their key
     */
    @PostMapping(value = "/user/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserInfo authenticate(@RequestBody LoginCredentialsDTO credentials, HttpServletResponse response) {
        Optional<UserInfo> userInfo = userInfoService.authenticate(credentials.getUsername(), credentials.getPassword());

        if (userInfo.isPresent()) {
            String jwt = jwtUtil.createJWT(userInfo.get());
            response.setHeader(jwtUtil.getHeader(), jwt);
        }

        return userInfo.get();
    }

    /**
     * Register Basic User
     * @param regData Registration Data from Frontend
     * @return The new registered User Data
     */
    @PostMapping(value = "/register/basic", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User registerBasicUser(@RequestBody @Valid UserRegistrationDTO regData, HttpServletResponse response){
        User user = userService.registerUser(regData, User.Role.BASIC);
        String jwt = jwtUtil.createJWT(user.getUserInfo());
        response.setHeader(jwtUtil.getHeader(), jwt);
        return user;
    }

    /**
     * Register Premium User
     * @param regData Registration Data from Frontend
     * @return The new registered User Data
     */
    @PostMapping(value = "/register/premium", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User registerPremiumUser(@RequestBody @Valid UserRegistrationDTO regData, HttpServletResponse response){
        System.out.println(regData);
        User user = userService.registerUser(regData, User.Role.PREMIUM);
        String jwt = jwtUtil.createJWT(user.getUserInfo());
        response.setHeader(jwtUtil.getHeader(), jwt);
        return user;
    }
}
