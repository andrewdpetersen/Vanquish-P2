package Application.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private boolean role;

    /**
     * Full args constructor
     * @param user_id
     * @param first_name
     * @param last_name
     * @param username
     * @param password
     * @param email
     * @param role
     */
    public User(Integer user_id, String first_name, String last_name, String username, String password, String email, boolean role) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    /**
     * No args constructor
     */
    public User() {
    }

    /**
     * Boilerplate getters and setters
     * @return
     */
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
}
