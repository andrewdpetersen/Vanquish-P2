package VanquishP2.Beans.ModelServices;

import VanquishP2.Beans.Models.User;
import VanquishP2.Beans.Models.UserInfo;
import VanquishP2.Beans.Service.Logger;
import VanquishP2.DTOs.UserRegistrationDTO;
import VanquishP2.Exceptions.AuthenticationException;
import VanquishP2.Exceptions.UserDoesNotExistException;
import VanquishP2.Repos.UserInfoRepository;
import VanquishP2.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 *
 * @date 10/29/2021
 * @author
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
     *
     *
     * @return
     * @author
     */
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     *
     * @param ID
     * @return
     * @author
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
     *
     * @param firstName
     * @return
     * @author
     */
    public User getByFirstName(String firstName) throws UserDoesNotExistException {
        return userRepository.findUserByFirstName(firstName)
                .orElseThrow(() -> new UserDoesNotExistException(String.format(exceptionError, firstName)));
    }

    /**
     *
     * @param user
     * @return
     */
    public void save(@Autowired User user) {
        userRepository.save(user);
    }

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws AuthenticationException
     */
    public Optional<User> authenticate(String username, String password) throws AuthenticationException {
        Optional<User> user = Optional.ofNullable(userInfoRepository.findUserByUsernameAndPassword(username, password).get().getUser());

        if (!user.isPresent()) {
            throw new AuthenticationException("Either you can't spell or your caps lock is on! Try again.");
        }
        return Optional.ofNullable(userInfoRepository.findUserByUsernameAndPassword(username, password).get().getUser());
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
     * @param userRegistrationDTOData
     * @return
     */
    public User registerUser(UserRegistrationDTO userRegistrationDTOData, User.Role role) {
        User newUser;
        UserInfo newUserInfo;

        newUserInfo = new UserInfo(userRegistrationDTOData);
        newUserInfo.setUsername(userRegistrationDTOData.getUsername());
        newUserInfo.setEmail(userRegistrationDTOData.getEmail());
        newUserInfo.setPassword(userRegistrationDTOData.getPassword());

        newUser = new User(userRegistrationDTOData);
        newUser.setRole(role);
        newUser.setUserInfo(newUserInfo);

        userInfoRepository.save(newUserInfo);
        userRepository.save(newUser);

        return newUser;
    }
}
