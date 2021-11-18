package Application.services;

import Application.exceptions.UserDoesNotExistException;
import Application.models.UserInfo;
import Application.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * This service bean is used to talk to its designated repository and handle data retrieval for 'UserInfo'
 *
 *
 * @date 11/8/2021
 * @author Andrew Peterson and Kollier Martin
 */
@Service
@Transactional
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final String exceptionError = "User: %s does not exist.";

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    /**
     *
     * @param id ID to query the database with
     * @return UserInfo
     */
    public UserInfo getUserInfoById(Integer id) {
        return userInfoRepository.getById(id);
    }

    /**
     * Saves and returns the persisted UserInfo
     * @param userInfo UserInfo object to save
     * @return Newly Persisted UserInfo
     */
    public UserInfo save(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    /**
     * This method deletes a persisted UserInfo object
     * @param info UserInfo object to delete
     */
    public void deleteUserInfo(UserInfo info){
        userInfoRepository.delete(info);
    }

    /**
     * This method is used to clear the UserInfo database
     */
    public void deleteAllInfo(){
        userInfoRepository.deleteAll();
    }

    /**
     * This method fetches all User Info in the DB
     * @return List of users present in DB
     */
    public List<UserInfo> getAll() {
        return userInfoRepository.findAll();
    }

    /**
     * Authenticates user presence in database
     * @param username Username
     * @param password Password
     * @return User Info, either null or not null
     */
    public Optional<UserInfo> authenticate(String username, String password){
        Optional<UserInfo> userInfo = userInfoRepository.findByUsernameAndPassword(username, password);

        if (!userInfo.isPresent()) {
            throw new UserDoesNotExistException(String.format(exceptionError, username));
        }

        return userInfo;
    }
}