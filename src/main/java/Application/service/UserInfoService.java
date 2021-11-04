package Application.service;

import Application.models.UserInfo;
import Application.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class UserInfoService {
    private UserInfoRepository userInfoRepository;

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

    public void deleteUserInfo(UserInfo info){
        userInfoRepository.delete(info);
    }

    /**
     * To reset DB data if necessary
     */
    public void deleteAllInfo(){
        userInfoRepository.deleteAll();
    }
}
