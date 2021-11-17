package Application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "tracks")
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
public class Track {
    /**
     * No args constructor
     */
    public Track() {
    }

    /**
     * Full args constructor
     * @param id
     * @param title
     */
    public Track(Integer id, String title) {
        this.track_id = id;
        this.title = title;
        this.userLikes = new ArrayList<>();
        this.userDislikes = new ArrayList<>();
    }

    public Track(String title, String duration, Artist artist) {
        this.title = title;
        this.duration = duration;
        this.artist = artist;
        this.userLikes = new ArrayList<>();
        this.userDislikes = new ArrayList<>();
    }

    public Track(Integer id, String title, Artist artist, Album album)
    {
        this.track_id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.userLikes = new ArrayList<>();
        this.userDislikes = new ArrayList<>();
    }

    public Track(Integer track_id, String title, String duration, List<Playlist> playlists, List<User> userLikes, List<User> userDislikes, Artist artist, Album album) {
        this.track_id = track_id;
        this.title = title;
        this.duration = duration;
        this.playlists = playlists;
        this.userLikes = userLikes;
        this.userDislikes = userDislikes;
        this.artist = artist;
        this.album = album;
    }

    @Id
    private Integer track_id;
    public Integer getTrack_id() {
        return track_id;
    }
    public void setTrack_id(Integer track_id) {
        this.track_id = track_id;
    }

    @Column
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

    //many tracks, many playlists (we want a list of what playlists this track is on)
    @ManyToMany(mappedBy = "trackList")
    @JsonIgnore
    private List<Playlist> playlists;
    public List<Playlist> getPlaylists() {
        return playlists;
    }
    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    //many tracks, many users
    @ManyToMany(mappedBy = "liked_tracks")
    @JsonIgnore
    List<User> userLikes;
    public List<User> getUserLikes() {
        return userLikes;
    }
    public void setUserLikes(List<User> userLikes) {
        this.userLikes = userLikes;
    }

    //many tracks, many users
    @ManyToMany(mappedBy = "disliked_tracks")
    @JsonIgnore
    List<User> userDislikes;
    public List<User> getUserDislikes() {
        return userDislikes;
    }
    public void setUserDislikes(List<User> userDislikes) {
        this.userDislikes = userDislikes;
    }

    @ManyToOne
    Artist artist;
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @ManyToOne
    Album album;
    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + track_id +
                ", title='" + title + '\'' +
                '}';
    }
}
