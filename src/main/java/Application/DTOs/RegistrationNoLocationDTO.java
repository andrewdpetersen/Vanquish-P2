package Application.DTOs;
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
public class RegistrationNoLocationDTO {

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

    private String firstName, lastName, city, state;

    public RegistrationNoLocationDTO() {

    }

    public RegistrationNoLocationDTO(String firstName, String lastName,
                                     String username, String password, String email, String city, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public RegistrationNoLocationDTO(String username, String password, String email) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "RegistrationNoLocationDTO {\n" +
                "username: " + username + ",\n" +
                "password: " + password + ",\n" +
                "email: " + email + ",\n" +
                "firstName: " + firstName + ",\n" +
                "lastName: " + lastName + ",\n" +
                "city: " + city + ",\n" +
                "state: " + state + ",\n" +
                '}';
    }
}

