package VanquishP2.Application.Beans.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.List;

@Table(name = "LOCATIONS")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location {
    public Location(String city, String state) {
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

    @OneToMany(mappedBy = "location")
    private List<UserInfo> userLocations;
    public List<UserInfo> getUserInfo() {
        return userLocations;
    }
    public void setUserInfo(List<UserInfo> userLocations) {
        this.userLocations = userLocations;
    }

    @Override
    public String toString() {
        return "Location {\n" +
                "locationID: " + locationID + ",\n" +
                "city: " + city + ",\n" +
                "state: " + state + ",\n" +
                "userLocations: " + userLocations + ",\n" +
                '}';
    }
}