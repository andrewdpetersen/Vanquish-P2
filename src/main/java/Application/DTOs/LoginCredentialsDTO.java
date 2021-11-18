package Application.DTOs;

/**
 * LoginCredentialsDTO
 * This DTO is used to receive login information from the front-end
 *
 * @date 11/4/2021
 * @author Kollier Martin
 */
public class LoginCredentialsDTO {

    private String username;
    private String password;

    public LoginCredentialsDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginCredentialsDTO() {
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

    @Override
    public String toString() {
        return "LoginCredentialsDTO {\n" +
                "username: " + username + ",\n" +
                "password: " + password + ",\n" +
                '}';
    }
}