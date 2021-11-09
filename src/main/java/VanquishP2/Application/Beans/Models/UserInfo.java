package VanquishP2.Application.Beans.Models;

import VanquishP2.DTOs.UserRegistrationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * This class is used to declare the POJO, UserInfo
 * @date 10/29/2021
 * @author Kollier Martin
 */

@Table(name = "USERINFO")
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "location", "user"},
        ignoreUnknown = true)
public class UserInfo {
    public UserInfo() {

    }

    public UserInfo(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserInfo(UserRegistrationDTO registration) {
        this.location = registration.getLocation();
        this.firstName = registration.getFirstName();
        this.lastName = registration.getLastName();
        this.username = registration.getUsername();
        this.password = registration.getPassword();
        this.email = registration.getEmail();
    }

    @Id
    @Column(name = "UserInfoID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
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

    @Column(name = "Email")
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "Username")
    private String username;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "Password", nullable = false)
    private String password;
    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Location location;
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    @OneToOne(cascade = CascadeType.REMOVE)
    User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserInfo {\n" +
                "ID: " + ID + ",\n" +
                "firstName: " + firstName + ",\n" +
                "lastName: " + lastName + ",\n" +
                "email: " + email + ",\n" +
                "username: " + username + ",\n" +
                "password: " + password + ",\n" +
                "location: " + location + ",\n" +
                '}';
    }
}
