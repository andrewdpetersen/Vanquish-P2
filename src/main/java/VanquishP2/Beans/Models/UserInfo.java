package VanquishP2.Beans.Models;

import VanquishP2.DTOs.UserRegistrationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * This class is used to declare the POJO, UserInfo
 * @date 10/29/2021
 * @author Kollier Martin
 */

@Table(name = "USERINFOS")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserInfo {
    public UserInfo() {

    }

    public UserInfo(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserInfo(UserRegistrationDTO registration) {
        this.username = registration.getUsername();
        this.password = registration.getPassword();
        this.email = registration.getEmail();
    }

    @Id
    @Column(name = "InfoID")
    private int ID;
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
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

    @OneToOne
    User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
