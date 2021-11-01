package Models;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;

/**
 * This class is used to declare the POJO, User
 * @date 10/29/2021
 * @author
 */

@Table(name = "USERS")
@Entity
public class User {
    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    @Column(name = "FIRST_NAME")
    private String firstName;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String username) {
        this.firstName = username;
    }

    @Column(name = "LAST_NAME")
    private String lastName;
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "EMAIL")
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "USERNAME")
    private String username;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD", nullable = false)
    private String password;
    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User: {\n" +
                "ID: " + ID +
                "firstName: " + firstName + ",\n" +
                "lastName: " + lastName + ",\n" +
                "email: " + email + ",\n" +
                "username: " + username + ",\n" +
                "password: " + password + "\n" +
                '}';
    }
}
