package Application.DTOs;

import Application.models.User;
import io.jsonwebtoken.Claims;

/**
 * Custom Authentication Principal
 * Used for validation of user
 * @author Kollier Martin
 * @date 11/8/2021
 */
public class PrincipalDTO {

    private int userID;
    private String username;
    private User.Role role;

    /**
     * Parameterized Constructor
     * @param jwtClaims Claims object that includes an authorized user's information
     */
    public PrincipalDTO(Claims jwtClaims) {
        this.userID = Integer.parseInt(jwtClaims.getId());
        this.username = jwtClaims.getSubject();
        this.role = User.Role.valueOf(jwtClaims.get("role", String.class));
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }
}
