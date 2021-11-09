package VanquishP2.Application.Beans.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to declare the POJO, User
 * @date 10/29/2021
 * @author Kollier Martin
 */

@Table(name = "USERS")
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"},
        ignoreUnknown = true)
public class User {
    public User() {
        this.role = Role.BASIC;
        this.myPlaylists = new ArrayList<>();
        this.favorites = new ArrayList<>();
        this.likedTracks = new ArrayList<>();
        this.dislikedTracks = new ArrayList<>();
        this.userInfo = null;
    }

    public User(Role role, UserInfo userInfo) {
        this.role = role;
        this.myPlaylists = new ArrayList<>();
        this.likedTracks = new ArrayList<>();
        this.dislikedTracks = new ArrayList<>();
        this.favorites = new ArrayList<>();
        this.userInfo = userInfo;
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
    private Role role;
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
    private List<Playlist> myPlaylists;
    public List<Playlist> getMyPlaylists() {
        return myPlaylists;
    }
    public void setMyPlaylists(List<Playlist> myPlaylists) {
        this.myPlaylists = myPlaylists;
    }

    @OneToMany(mappedBy = "user")
    private List<Track> favorites;
    public List<Track> getFavorites() {
        return favorites;
    }
    public void setFavorites(List<Track> favorites) {
        this.favorites = favorites;
    }

    @OneToOne(mappedBy = "user")
    private UserInfo userInfo;
    public UserInfo getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @ManyToMany
    private List<Track> likedTracks;
    public List<Track> getLikedTracks() {
        return likedTracks;
    }
    public void setLikedTracks(List<Track> likedTracks) {
        this.likedTracks = likedTracks;
    }

    @ManyToMany
    private List<Track> dislikedTracks;
    public List<Track> getDislikedTracks() {
        return dislikedTracks;
    }
    public void setDislikedTracks(List<Track> dislikedTracks) {
        this.dislikedTracks = dislikedTracks;
    }

    @Override
    public String toString() {
        return "User {\n" +
                "ID: " + ID + ",\n" +
                "role: " + role + ",\n" +
                "myPlaylists: " + myPlaylists + ",\n" +
                "favorites: " + favorites + ",\n" +
                '}';
    }
}
