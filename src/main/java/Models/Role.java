package Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to declare the POJO, Role
 * @date 11/01/2021
 * @author
 */

@Table(name = "ROLES")
@Entity(name = "ROLE")
public class Role{
    public Role(){
        users = new ArrayList<>();
    }

    public Role(int roleID) {
        this.roleID = roleID;
        users = new ArrayList<>();
    }

    @Id
    @Column(name = "ROLE_ID", columnDefinition = "int default 0")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleID;
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Column(name = "USER_ROLE")
    private String userRole;
    public String getUserRole() {
        switch (roleID){
            case 0:
                userRole = ("Basic");
                break;
            case 1:
                userRole = ("Premium");
                break;
        }
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    // A Role contains a list of users under that designated role
    @OneToMany (cascade = CascadeType.ALL)
    private List<User> users;
    public List<User> getUserInfo() {
        return users;
    }
    public void setUserInfo(List<User> userRoles) {
        this.users = users;
    }
}
