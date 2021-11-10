package VanquishP2.Controllers;

import VanquishP2.Application.Beans.Models.User;
import VanquishP2.Application.Beans.ModelServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * UserController
 * Handles requests that involve the manipulating or retrieval of user data
 *
 * @date 11/1/2021
 * @author Kollier Martin
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

    /**
     * Get all users in DB
     * @return List of Registered Users
     */
    @GetMapping("/user/all")
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
     * Delete user by ID
     * @param id User ID
     * @return Response Entity
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
