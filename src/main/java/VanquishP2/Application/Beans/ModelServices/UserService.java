package VanquishP2.Application.Beans.ModelServices;

import VanquishP2.Application.Beans.Models.User;
import VanquishP2.Application.Beans.Models.UserInfo;
import VanquishP2.Application.Beans.Service.Logger;
import VanquishP2.DTOs.UserRegistrationDTO;
import VanquishP2.Exceptions.UserDoesNotExistException;
import VanquishP2.Application.Beans.Repos.UserInfoRepository;
import VanquishP2.Application.Beans.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * This service bean is used to talk to its designated repository and handle data retrieval
 *
 * @date 10/29/2021
 * @author Kollier Martin
 */

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final String exceptionError = "User: %s does not exist.";

    @Autowired
    public UserService(UserRepository userRepository,
                       UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * This method fetches all Users in the DB
     * @return List of users present in DB
     */
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * This method fetches User by ID
     * @param ID User ID
     * @return User object
     */
    public User getByID(int ID) {
        User user = null;

        try {
            user = userRepository.findByID(ID)
                    .orElseThrow(() -> new UserDoesNotExistException(String.format(exceptionError, ID)));
        } catch (UserDoesNotExistException e) {
            Logger.getFileLogger().writeLog(e.getMessage(), 3);
        }

        return user;
    }

    /**
     * This method fetches a User based on UserInfo
     * @param userInfo UserInfo object
     * @return User object
     */
    public User getByUserInfo(UserInfo userInfo) {
        User user = null;

        try {
            user = userRepository.findUserByUserInfo(userInfo)
                    .orElseThrow(() -> new UserDoesNotExistException(String.format(exceptionError, userInfo)));
        } catch (UserDoesNotExistException e) {
            Logger.getFileLogger().writeLog(e.getMessage(), 3);
        }

        return user;
    }

    /**
     *
     * @param user
     * @return
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     *
     * @param id
     * @return
     */
    public void delete(int id) throws UserDoesNotExistException {
        userRepository.deleteByID(id);
    }

    /**
     *
     * @param data
     * @return
     */
    public User registerUser(UserRegistrationDTO data, User.Role role) {
        User newUser;
        UserInfo newUserInfo;

        newUserInfo = new UserInfo(data);
        newUser = new User(role, newUserInfo);

        userRepository.save(newUser);

        newUserInfo.setUser(newUser);
        userInfoRepository.save(newUserInfo);

        return newUser;
    }
}
