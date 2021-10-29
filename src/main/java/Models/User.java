package Models;

import javax.persistence.*;

@Table(name = "USERS")
@Entity
public class User {
    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "User {\n" +
                "\tID: " + ID + "\n" +
                "\tfirstName: " + firstName + '\n' +
                "\tlastName: " + lastName + '\n' +
                "\temail: " + email + '\n' +
                '}';
    }

    @OneToOne
    private UserInfo userInfo;
    public UserInfo getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
