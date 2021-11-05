package Application.controllers;

import Application.models.UserInfo;
import Application.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/UserInfo")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping(value = "/userinfo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public UserInfo getUserInfoById(@PathVariable ("id") Integer id){
        return userInfoService.getUserInfoById(id);
    }

    @PostMapping(value = "/userinfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserInfo saveUserInfo(@RequestBody UserInfo userInfo){
        return userInfoService.saveUserInfo(userInfo);
    }

    @PutMapping(value = "/userinfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public UserInfo updateUserInfo(@RequestBody UserInfo userInfo){
        UserInfo updatedInfo = userInfoService.getUserInfoById(userInfo.getID());
        updatedInfo.setEmail(userInfo.getEmail());
        updatedInfo.setPassword(userInfo.getPassword());
        updatedInfo.setFirstName(userInfo.getFirstName());
        updatedInfo.setLastName(userInfo.getLastName());
        updatedInfo.setUser(userInfo.getUser());
        updatedInfo.setLocation(userInfo.getLocation());
        updatedInfo.setUsername(userInfo.getUsername());
        userInfoService.saveUserInfo(updatedInfo);
        return userInfoService.getUserInfoById(updatedInfo.getID());
    }

    @DeleteMapping(value = "/userinfo/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUserInfoById(@PathVariable ("id") Integer id){
        if(id>0){
            userInfoService.deleteUserInfo(userInfoService.getUserInfoById(id));
        }else{
            userInfoService.deleteAllInfo();
        }
    }
}
