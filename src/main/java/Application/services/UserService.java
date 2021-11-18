package Application.services;

import Application.DTOs.UserRegistrationDTO;
import Application.exceptions.UserDoesNotExistException;
import Application.models.Location;
import Application.models.User;
import Application.models.UserInfo;
import Application.repositories.LocationRepository;
import Application.repositories.UserInfoRepository;
import Application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * This service bean is used to talk to its designated repository and handle data retrieval for 'User'
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
    private final LocationRepository locationRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserInfoRepository userInfoRepository,LocationRepository locationRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.locationRepository = locationRepository;
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
            e.printStackTrace();
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
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Persist User to DB
     * @param user User to persist
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Delete User by ID
     * @param id ID used to query database
     */
    public void delete(int id) throws UserDoesNotExistException {
        userRepository.deleteByID(id);
    }

    /**
     * Register New User
     * @param data UserRegistration data to persist
     * @return The new persisted Object
     */
    public User registerUser(UserRegistrationDTO data, User.Role role) {
        User newUser;
        UserInfo newUserInfo;
        Location location;

        location = new Location(data.getCity(), data.getState());
        newUserInfo = new UserInfo(data);
        newUser = new User(role, newUserInfo);

        locationRepository.save(location);
        userRepository.save(newUser);

        newUserInfo.setLocation(location);
        newUserInfo.setUser(newUser);
        userInfoRepository.save(newUserInfo);

        return newUser;
    }
}
