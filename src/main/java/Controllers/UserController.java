package Controllers;

import Models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @CrossOrigin
    @GetMapping("/{id}")
    public User retrieve(@PathVariable int id) {
        User user = null;

        // User with this ID
        // Query<User> query = getSession().createQuery("from User where ID = :id", User.class);

        return user;
    }
}
