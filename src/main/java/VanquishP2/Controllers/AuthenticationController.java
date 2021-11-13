package VanquishP2.Controllers;

import VanquishP2.Application.Beans.ModelServices.UserInfoService;
import VanquishP2.Application.Beans.ModelServices.UserService;
import VanquishP2.Application.Beans.Models.User;
import VanquishP2.Application.Beans.Models.UserInfo;
import VanquishP2.Application.Beans.Service.JWTUtil;
import VanquishP2.DTOs.LoginCredentialsDTO;
import VanquishP2.DTOs.UserRegistrationDTO;
import jdk.nashorn.api.scripting.JSObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;
import static org.springframework.http.MediaType.*;

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
    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> authenticate(@RequestBody LoginCredentialsDTO credentials, HttpServletResponse response) {
        System.out.println(credentials);
        Optional<UserInfo> userInfo = userInfoService.authenticate(credentials.getUsername(), credentials.getPassword());

        if (userInfo.isPresent()) {
            String jwt = jwtUtil.createJWT(userInfo.get());
            response.setHeader(jwtUtil.getHeader(), jwt);
            return new ResponseEntity<>(userInfo.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Register Basic User
     * @param regData Registration Data from Frontend
     * @return The new registered User Data
     */
    @PostMapping(value = "/register/basic", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerBasicUser(@RequestBody @Valid UserRegistrationDTO regData) {
        User user = userService.registerUser(regData, User.Role.BASIC);
        String jwt = jwtUtil.createJWT(user.getUserInfo());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(jwtUtil.getHeader(), jwt);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(user);
    }

    /**
     * Register Premium User
     * @param regData Registration Data from Frontend
     * @return The new registered User Data
     */
    @PostMapping(value = "/register/premium", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerPremiumUser(@RequestBody @Valid UserRegistrationDTO regData){
        User user = userService.registerUser(regData, User.Role.PREMIUM);
        String jwt = jwtUtil.createJWT(user.getUserInfo());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(jwtUtil.getHeader(), jwt);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(user);
    }
}
