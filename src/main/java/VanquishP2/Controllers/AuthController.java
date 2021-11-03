package VanquishP2.Controllers;

import VanquishP2.Beans.ModelServices.UserService;
import VanquishP2.Beans.Models.User;
import VanquishP2.Beans.Service.JWTUtil;
import VanquishP2.DTOs.LoginCredentialsDTO;
import VanquishP2.DTOs.UserDTO;
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
    private UserService userService;

    @Autowired
    public AuthController (JWTUtil jwtUtil, UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserDTO authenticate(@RequestBody LoginCredentialsDTO credentials, HttpServletResponse response) {
        Optional<User> user = userService.authenticate(credentials.getUsername(), credentials.getPassword());
        String jwt = jwtUtil.createJWT(user.get());
        response.setHeader(jwtUtil.getHeader(), jwt);

        return new UserDTO(user.get());
    }

}
