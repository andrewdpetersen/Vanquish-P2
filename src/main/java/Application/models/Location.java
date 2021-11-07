package Application.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Table(name = "locations")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer location_id;
    public Integer getLocation_id() {
        return location_id;
    }
    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    @Column
    private String city;
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Column
    private String state;
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

<<<<<<< HEAD
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
=======
    @OneToMany(mappedBy = "location")
    private List<UserInfo> userInfoList;
    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }
    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public Location(Integer location_id, String city, String state) {
        this.location_id = location_id;
        this.city = city;
        this.state = state;
    }

    public Location() {
>>>>>>> bd93c80105452fa6d2fc22b344109e5ed39147a9
    }

    @Override
    public String toString() {
        return "Location{" +
                "location_id=" + location_id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
