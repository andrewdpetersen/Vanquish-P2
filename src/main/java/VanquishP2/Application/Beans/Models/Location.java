package VanquishP2.Application.Beans.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Table(name = "LOCATIONS")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location {
    public Location(Integer locationID, String city, String state) {
        this.locationID = locationID;
        this.city = city;
        this.state = state;
    }

    public Location() {

    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationID;
    public Integer getLocationID() {
        return locationID;
    }
    public void setLocationID(Integer location_id) {
        this.locationID = location_id;
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

    @OneToOne
    private UserInfo userInfo;
    public UserInfo getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "Location {\n" +
                "locationID: " + locationID + ",\n" +
                "city: " + city + ",\n" +
                "state: " + state + ",\n" +
                "userInfo: " + userInfo + ",\n" +
                '}';
    }
}