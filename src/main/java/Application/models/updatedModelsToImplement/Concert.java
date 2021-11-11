package VanquishP2.Application.Beans.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to declare the POJO, Concert
 * @date 11/01/2021
 * @author Kollier Martin
 */

@Table(name = "CONCERTS")
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
public class Concert {
    public Concert() {
        artists = new ArrayList<>();
    }

    @Id
    @Column(name = "ConcertID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Column(name = "Location")
    private String location;
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    @ManyToMany(mappedBy = "concerts")
    List<Artist> artists;
    public List<Artist> getArtists() {
        return artists;
    }
    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public String toString() {
        return "Concert {\n" +
                "ID: " + ID + ",\n" +
                "location: " + location + ",\n" +
                "artists: " + artists + ",\n" +
                '}';
    }
}
