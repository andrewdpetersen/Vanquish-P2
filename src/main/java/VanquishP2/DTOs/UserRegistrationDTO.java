package VanquishP2.DTOs;

import VanquishP2.Application.Beans.Models.Location;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * UserRegistrationDTO
 * The DTO for User Registration
 *
 * @date 10/29/2021
 * @author Kollier Martin
 */
public class UserRegistrationDTO {

    @Length(min = 5, max = 20)
    @NotNull(message = "Null, a username can not be.")
    @Pattern(message = "Username is not valid for the supreme overlord. Try again.",
            regexp = "^[a-zA-Z0-9]*$")
    private String username;

    @NotNull(message = "*crickets*. There's no password here.")
    @Pattern(message = "Come on. Your password should be strong, like Russian.",
            regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
    private String password;

    @Email(message = "This email is not valid >:(")
    private String email;

    private String firstName, lastName;
    private Location location;

    public UserRegistrationDTO() {

    }

    public UserRegistrationDTO(String firstName, String lastName,
                               String username, String password, String email, Location location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserRegistrationDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO {\n" +
                "username: " + username + ",\n" +
                "password: " + password + ",\n" +
                "email: " + email + ",\n" +
                "firstName: " + firstName + ",\n" +
                "lastName: " + lastName + ",\n" +
                "location: " + location + ",\n" +
                '}';
    }
}