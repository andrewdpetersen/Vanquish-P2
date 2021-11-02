package Services;

import Models.User;
import Service.UserRegistration;
import Exceptions.UserDoesNotExistException;
import Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 *
 * @date 10/29/2021
 * @author
 */

@Service
@Transactional
public class UserService {
    // Repository
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *
     *
     * @return
     * @author
     */
    public ArrayList<User> allUsers(){
        ArrayList<User> users = new ArrayList<>();

        // Query for all users in database

        return users;
    }

    /**
     *
     * @param ID
     * @return
     * @author
     */
    public User getByID(int ID){
        return userRepository.findByID(ID)
                .orElseThrow(UserDoesNotExistException::new);
    }

    /**
     *
     * @param user
     * @return
     */
    public boolean save(@Autowired User user){
        boolean success;

        try{
            userRepository.save(user);
            success = true;
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }

        return success;
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean delete(int id){
        boolean success;

        try {
            User user = getByID(id);
            if (user != null) {
                userRepository.delete(user);
                success = true;
            } else {
                success = false;
            }
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }

        return success;
    }

    /**
     *
     * @param userRegistrationData
     * @return
     */
    public User registerUser(UserRegistration userRegistrationData){
        User newUser = null;
        String username, password, email;

        username = userRegistrationData.getUsername();
        email = userRegistrationData.getEmail();
        password = userRegistrationData.getPassword();

        try {
            newUser = new User(email, username, password);
            newUser.setRole(User.Role.BASIC);
            userRepository.save(newUser);
        } catch (Exception e){

        }

        return newUser;
    }
}
