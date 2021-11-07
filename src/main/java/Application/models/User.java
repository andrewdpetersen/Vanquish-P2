package Application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to declare the POJO, User
 * @date 10/29/2021
 * @author Kollier Martin
 */

@Component
@Table(name = "users")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    public User() {
        myPlaylists = new ArrayList<>();
    }

    @Id
    @Column(name = "UserID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Enumerated
    Role role;
    public enum Role {
        BASIC,
        PREMIUM
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Playlist> myPlaylists;
    public List<Playlist> getMyPlaylists() {
        return myPlaylists;
    }
    public void setMyPlaylists(List<Playlist> myPlaylists) {
        this.myPlaylists = myPlaylists;
    }

    @ManyToMany
    @JsonIgnore
    List<Track> liked_tracks;
    public List<Track> getLiked_tracks() {
        return liked_tracks;
    }
    public void setLiked_tracks(List<Track> liked_tracks) {
        this.liked_tracks = liked_tracks;
    }

    @ManyToMany
    @JsonIgnore
    List<Track> disliked_tracks;
    public List<Track> getDisliked_tracks() {
        return disliked_tracks;
    }
    public void setDisliked_tracks(List<Track> disliked_tracks) {
        this.disliked_tracks = disliked_tracks;
    }

    @OneToOne(mappedBy = "user")
    UserInfo userInfo;
    public UserInfo getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Track> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Track> favorites) {
        this.favorites = favorites;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", role=" + role +
                ", myPlaylists=" + myPlaylists +
                ", liked_tracks=" + liked_tracks +
                ", disliked_tracks=" + disliked_tracks +
                ", userInfo=" + userInfo +
                '}';
    }
}