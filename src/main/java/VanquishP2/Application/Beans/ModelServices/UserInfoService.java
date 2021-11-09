package VanquishP2.Application.Beans.ModelServices;

import VanquishP2.Application.Beans.Models.User;
import VanquishP2.Application.Beans.Models.UserInfo;
import VanquishP2.Application.Beans.Repos.UserInfoRepository;
import VanquishP2.Application.Beans.Service.Logger;
import VanquishP2.Exceptions.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final String exceptionError = "User: %s does not exist.";

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo saveUserInfo(UserInfo userInfo){
        userInfoRepository.save(userInfo);
        return userInfoRepository.getById(userInfo.getID());
    }

    public UserInfo getUserInfoById(Integer id){
        return userInfoRepository.getById(id);
    }

    public String getEmailById(Integer id){
        UserInfo info = userInfoRepository.getById(id);
        return info.getEmail();
    }

    public String getUsernameById(Integer id){
        UserInfo info = userInfoRepository.getById(id);
        return info.getUsername();
    }

    public String getPasswordById(Integer id){
        UserInfo info = userInfoRepository.getById(id);
        return info.getPassword();
    }

    public List<String> getLoginById(Integer id){
        UserInfo info = userInfoRepository.getById(id);
        List<String> login = new LinkedList<>();
        login.add(info.getUsername());
        login.add(info.getPassword());
        return login;
    }

    public UserInfo getByFirstName(String firstName) {
        return userInfoRepository.findByFirstName(firstName).get();
    }

    public void save(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }

    public void deleteUserInfo(UserInfo info){
        userInfoRepository.delete(info);
    }

    /**
     * To reset DB data if necessary
     */
    public void deleteAllInfo(){
        userInfoRepository.deleteAll();
    }

    /**
     * This method fetches all User Info in the DB
     * @author Kollier Martin
     * @date 11/8/2021
     * @return List of users present in DB
     */
    public List<UserInfo> getAll() {
        return userInfoRepository.findAll();
    }

    /**
     * Authenticate User
     * @date 11/8/2021
     * @author Kollier Martin
     * @param username Username
     * @param password Password
     * @return User Info, either null or not null
     */
    public Optional<UserInfo> authenticate(String username, String password){
        Optional<UserInfo> userInfo = Optional.empty();

        try {
            userInfo = userInfoRepository.findByUsernameAndPassword(username, password);
            if (!userInfo.isPresent()) {
                throw new UserDoesNotExistException(exceptionError);
            }
        } catch (UserDoesNotExistException e) {
            Logger.getFileLogger().writeLog(e.getMessage(), 3);
        }

        return userInfo;
    }
}