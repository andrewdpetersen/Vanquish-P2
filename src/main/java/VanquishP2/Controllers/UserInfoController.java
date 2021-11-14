package VanquishP2.Controllers;


import VanquishP2.Application.Beans.ModelServices.UserInfoService;
import VanquishP2.Application.Beans.Models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/4TheMusic")
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

    // Kollier took out setLocation and setUser until we get the Front End together
    @PutMapping(value = "/userinfo/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public UserInfo updateUserInfo(@RequestBody UserInfo userInfo){
        UserInfo updatedInfo = userInfoService.getUserInfoById(userInfo.getID());
        updatedInfo.setEmail(userInfo.getEmail());
        updatedInfo.setPassword(userInfo.getPassword());
        updatedInfo.setFirstName(userInfo.getFirstName());
        updatedInfo.setLastName(userInfo.getLastName());
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

    /**
     * Get all users in DB
     * @return List of Registered Users
     * @author Kollier Martin
     */
    @GetMapping("/userinfo/all")
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        List<UserInfo> allUsers = userInfoService.getAll();

        if (allUsers == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (allUsers.isEmpty())
            return new ResponseEntity<>(allUsers, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}