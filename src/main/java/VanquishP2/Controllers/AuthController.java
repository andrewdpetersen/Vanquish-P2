package VanquishP2.Controllers;

import VanquishP2.Application.Beans.ModelServices.UserInfoService;
import VanquishP2.Application.Beans.Models.UserInfo;
import VanquishP2.Application.Beans.Service.JWTUtil;
import VanquishP2.DTOs.LoginCredentialsDTO;
import VanquishP2.DTOs.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/authenticate")
public class AuthController {

    private JWTUtil jwtUtil;
    private final UserInfoService userInfoService;

    @Autowired
    public AuthController (JWTUtil jwtUtil, UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserInfoDTO authenticate(@RequestBody LoginCredentialsDTO credentials, HttpServletResponse response) {
        Optional<UserInfo> userInfo = userInfoService.authenticate(credentials.getUsername(), credentials.getPassword());
        String jwt = jwtUtil.createJWT(userInfo.get());
        response.setHeader(jwtUtil.getHeader(), jwt);

        return new UserInfoDTO(userInfo.get());
    }

}
