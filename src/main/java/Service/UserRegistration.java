package Service;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Service
public class UserRegistration {

    @Length(min = 5, max = 20)
    @NotNull(message = "Null, a username can not be.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String username;

    @Length(min = 8, max = 20)
    @NotNull(message = "Come on. Your password should be strong, like Russian.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>])$")
    private String password;

    @Email(message = "This email is not valid >:(")
    private String email;

    public UserRegistration() {

    }

    public UserRegistration(String username, String password, String email) {
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
}