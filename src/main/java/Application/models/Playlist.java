package Application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlists")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
public class Playlist {
    public Playlist() {
        trackList = new ArrayList<>();
    }

    public Playlist(String playlistName, User user, List<Track> trackList) {
        this.playlistName = playlistName;
        this.user = user;
        this.trackList = trackList;
    }

    @Id
    @Column(name = "PlaylistID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playlist_id;
    public Integer getPlaylist_id() {
        return playlist_id;
    }
    public void setID(Integer playlist_id) {
        this.playlist_id = playlist_id;
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

    @ManyToMany
    List<Track> trackList;
    public List<Track> getTrackList() {
        return trackList;
    }
    public void setTrackList(List<Track> tracks) {
        this.trackList = tracks;
    }

    @Override
    public String toString() {
        return "Playlist {\n" +
                "playlist_id: " + playlist_id + ",\n" +
                "playlistName: " + playlistName + ",\n" +
                "user: " + user + ",\n" +
                "tracks: " + trackList + ",\n" +
                '}';
    }
}