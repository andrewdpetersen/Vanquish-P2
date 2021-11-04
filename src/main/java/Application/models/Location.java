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

    @Column
    private String city;

    @Column
    private String state;

    @OneToMany(mappedBy = "location")
    private List<UserInfo> userInfoList;

    public Location(Integer location_id, String city, String state) {
        this.location_id = location_id;
        this.city = city;
        this.state = state;
    }

    public Location() {
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
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
        return "Location{" +
                "location_id=" + location_id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
