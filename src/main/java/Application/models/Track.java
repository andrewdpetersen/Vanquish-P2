package Application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * If @Component (defines a java class as a bean) not added will throw a NoSuchBeanDefinitionException
 */

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "tracks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Track implements Serializable {
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

    /**
     * Full args constructor
     * @param id
     * @param title
     */
    public Track(Integer id, String title) {
        this.track_id = id;
        this.title = title;
    }

    /**
     * No args constructor
     */
    public Track() {
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + track_id +
                ", title='" + title + '\'' +
                '}';
    }
}
