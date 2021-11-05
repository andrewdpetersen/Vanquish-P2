package Application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "playlists")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "playlist_id")
    private int playlist_id;

    @Column(name="playlist_name")
    String playlist_name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //many tracks on many playlists
    @JsonIgnore
    @ManyToMany
    private List<Track> trackList;

    /**
     * Full args constructor
     * @param playlist_id
     * @param playlist_name
     * @param user
     * @param trackList
     */
    public Playlist(int playlist_id, String playlist_name, User user, List<Track> trackList) {
        this.playlist_id = playlist_id;
        this.playlist_name = playlist_name;
        this.user = user;
        this.trackList = trackList;
    }

    /*
     * No args constructor
     */
    public Playlist(){
    }

    public int getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(int playlist_id) {
        this.playlist_id = playlist_id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlist_id=" + playlist_id +
                ", playlist_name='" + playlist_name + '\'' +
                ", user=" + user +
                ", trackList=" + trackList +
                '}';
    }
}