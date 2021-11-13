package Application.controllers;

import Application.DTOs.RegistrationNoLocationDTO;

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
@RequestMapping(value = "/4TheMusic", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Receives ID from request, then returns a User object if they exist
     * @param id ID Integer to distinguish user
     * @return User Object
     */
    @GetMapping("/user/{id}")
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
     * Register Basic User to server
     * @param regData Registration Data
     * @return The registered User
     */
    @PostMapping(value = "/user/register/basic", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User registerBasicUser(@RequestBody @Valid RegistrationNoLocationDTO regData){
        return userService.registerUser(regData, User.Role.BASIC);
    }

    /**
     * Register Register User to server
     * @param regData Registration Data
     * @return The registered User
     */
    @PostMapping(value = "/user/register/premium", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User registerPremiumUser(@RequestBody @Valid RegistrationNoLocationDTO regData){
        return userService.registerUser(regData, User.Role.PREMIUM);
    }

    /**
     * Delete User from DB
     * @param id User ID
     * @return An empty user object to show that the user is gone
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") int id) {
        User user = userService.getByID(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);
        return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
    }
}
