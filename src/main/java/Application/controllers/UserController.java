package Application.controllers;

import Application.DTOs.UserRegistrationDTO;
import Application.models.User;
import Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * UserController
 * Handles requests that involve the manipulating or retrieval of user data
 *
 * @Date 11/1/2021
 * @Author Kollier Martin
 */

@RestController
@RequestMapping(value = "/user", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;
    //private final JWTUtil jwtUtil;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        //this.jwtUtil = jwtUtil;
    }

    /**
     * Receives ID from request, then returns a User object if they exist
     * @param id ID Integer to distinguish user
     * @return User Object
     */
    @GetMapping("/{id}")
    public User retrieve(@PathVariable int id) {
        return userService.getByID(id);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();

        if (allUsers == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (allUsers.isEmpty())
            return new ResponseEntity<>(allUsers, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    /**
     *
     * @param regData
     * @return
     */
    @PostMapping(value = "/register/basic", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User registerBasicUser(@RequestBody @Valid UserRegistrationDTO regData){
        return userService.registerUser(regData, User.Role.BASIC);
    }

    /**
     *
     * @param regData
     * @return
     */
    @PostMapping(value = "/register/premium", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User registerPremiumUser(@RequestBody @Valid UserRegistrationDTO regData){
        return userService.registerUser(regData, User.Role.PREMIUM);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") int id) {
        User user = userService.getByID(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);
        return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
    }
}