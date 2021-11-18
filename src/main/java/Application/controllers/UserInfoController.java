package Application.controllers;

import Application.models.UserInfo;
import Application.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserInfoController
 * Handles requests that involve the manipulation of UserInfo data
 *
 * @date 11/8/2021
 * @author Andrew Peterson
 */
@RestController
@RequestMapping(value = "/4TheMusic")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/userinfo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public UserInfo getUserInfoById(@PathVariable ("id") Integer id){
        return userInfoService.getUserInfoById(id);
    }

    /**
     *
     * @param userInfo
     * @return
     */
    @PostMapping(value = "/userinfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserInfo saveUserInfo(@RequestBody UserInfo userInfo){
        return userInfoService.save(userInfo);
    }

    /**
     *
     * @param userInfo
     * @return
     */
    @PutMapping(value = "/userinfo/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public UserInfo updateUserInfo(@RequestBody UserInfo userInfo){
        UserInfo updatedInfo = userInfoService.getUserInfoById(userInfo.getID());

        updatedInfo.setEmail(userInfo.getEmail());
        updatedInfo.setPassword(userInfo.getPassword());
        updatedInfo.setLocation(userInfo.getLocation());
        updatedInfo.setUser(userInfo.getUser());
        updatedInfo.setFirstName(userInfo.getFirstName());
        updatedInfo.setLastName(userInfo.getLastName());
        updatedInfo.setUsername(userInfo.getUsername());

        return userInfoService.save(updatedInfo);
    }

    /**
     *
     * @param id
     */
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
