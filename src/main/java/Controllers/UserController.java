package Controllers;

import Models.User;
import Service.UserRegistration;
import Repos.UserRepository;
import Services.UserService;
import Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * UserController
 * Handles requests that involve the manipulating or retrieval of user data
 *
 * @Date 11/1/2021
 * @Author Vanquish
 */

@RestController
@RequestMapping(value = "/user", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Receives ID from request, then returns a User object if they exist
     * @param id ID Integer to distinguish user
     * @return User Object
     */
    @GetMapping("/{id}")
    public User retrieve(@PathVariable int id) {
        User user = userService.getByID(id);

        return user;
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<User>> getAllUsers() {
        ArrayList<User> allUsers = userService.allUsers();

        if (allUsers == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (allUsers.isEmpty())
            return new ResponseEntity<>(allUsers, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User registerUser(@RequestBody @Valid UserRegistration regData){
        return userService.registerUser(regData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") int id) {
        Optional<User> user = Optional.ofNullable(userService.getByID(id));

        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);
        return new ResponseEntity<>(user.get(), HttpStatus.NO_CONTENT);
    }
}
