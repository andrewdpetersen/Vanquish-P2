package VanquishP2.Application.Beans.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * This class is used to declare the POJO, Track
 * @date 11/01/2021
 * @author Kollier Martin
 */

@Table(name = "TRACKS")
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
public class Track {
    public Track() {

    }

    public Track(Integer id, String title) {
        this.trackID = id;
        this.title = title;
    }

    public Track(String title, String duration, Artist artist) {
        this.title = title;
        this.duration = duration;
        this.artist = artist;
    }

    @Id
    @Column(name = "TrackID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trackID;
    public Integer getTrackID() {
        return trackID;
    }
    public void setTrackID(Integer ID) {
        this.trackID = ID;
    }

    @Column(name = "Title")
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    Album album;
    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }

    @ManyToMany(mappedBy = "trackList")
    private List<Playlist> playlists;
    public List<Playlist> getPlaylists() {
        return playlists;
    }
    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    @ManyToMany(mappedBy = "likedTracks")
    private List<User> userLikes;
    public List<User> getUserLikes() {
        return userLikes;
    }
    public void setUserLikes(List<User> userLikes) {
        this.userLikes = userLikes;
    }

    @ManyToMany(mappedBy = "dislikedTracks")
    private List<User> userDislikes;
    public List<User> getUserDislikes() {
        return userDislikes;
    }
    public void setUserDislikes(List<User> userDislikes) {
        this.userDislikes = userDislikes;
    }

    @Override
    public String toString() {
        return "Track {\n" +
                "ID: " + trackID + ",\n" +
                "title: " + title + ",\n" +
                "duration: " + duration + ",\n" +
                "artist: " + artist + ",\n" +
                "user: " + user + ",\n" +
                "playlists: " + playlists + ",\n" +
                '}';
    }
}
