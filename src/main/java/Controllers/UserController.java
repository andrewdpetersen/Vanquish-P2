package Controllers;

import Models.User;
import Services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @CrossOrigin
    @GetMapping("/{id}")
    public User retrieve(@PathVariable int id) {
        User user = UserService.getByID(id);

        return user;
    }
}
