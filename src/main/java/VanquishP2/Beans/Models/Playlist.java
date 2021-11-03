package VanquishP2.Beans.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to declare the POJO, Playlist
 * @date 10/29/2021
 * @author Kollier Martin
 */

@Table(name = "PLAYLISTS")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Playlist {
    public Playlist() {
        tracks = new ArrayList<>();
    }

    @Id
    @Column(name = "PlaylistID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Column(name = "PlaylistName")
    private String playlistName;
    public String getPlaylistName() {
        return playlistName;
    }
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "playlist")
    List<Track> tracks;
    public List<Track> getTracks() {
        return tracks;
    }
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
