package VanquishP2.Application.Beans.Models;

import VanquishP2.DTOs.UserRegistrationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to declare the POJO, User
 * @date 10/29/2021
 * @author Kollier Martin
 */

@Table(name = "USERS")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    public User() {
        myPlaylists = new ArrayList<>();
    }

    public User(UserRegistrationDTO registration) {
        this.firstName = registration.getFirstName();
        this.lastName = registration.getLastName();
        myPlaylists = new ArrayList<>();
    }

    @Id
    @Column(name = "UserID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Column(name = "FirstName")
    private String firstName;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String username) {
        this.firstName = username;
    }

    @Column(name = "LastName")
    private String lastName;
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Enumerated
    Role role;
    public enum Role {
        BASIC,
        PREMIUM
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "user")
    List<Playlist> myPlaylists;
    public List<Playlist> getMyPlaylists() {
        return myPlaylists;
    }
    public void setMyPlaylists(List<Playlist> myPlaylists) {
        this.myPlaylists = myPlaylists;
    }

    @OneToOne(mappedBy = "user")
    UserInfo userInfo;
    public UserInfo getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "User: {\n" +
                "ID: " + ID +
                "firstName: " + firstName + ",\n" +
                "lastName: " + lastName + ",\n" +
                '}';
    }
}
