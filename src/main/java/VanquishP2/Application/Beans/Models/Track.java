package VanquishP2.Application.Beans.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * This class is used to declare the POJO, Track
 * @date 11/01/2021
 * @author Kollier Martin
 */

@Table(name = "TRACKS")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Track {
    public Track() {
    }

    @Id
    @Column(name = "TrackID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Column(name = "TrackName")
    private String trackName;
    public String getTrackName() {
        return trackName;
    }
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    @Column(name = "Duration")
    private String duration;
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    @ManyToOne
    private Artist artist;
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @ManyToOne
    private Playlist playlist;
    public Playlist getPlaylist() {
        return playlist;
    }
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
