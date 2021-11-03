package Application.models;

import javax.persistence.*;

@Entity
@Table(name="playlist")
public class Playlist {
    @Id
    @GeneratedValue
    @Column(name = "playlist_id")
    private int playlist_id;

    @Column(name="user_id")
    private int user_id;

    @Column(name="playlist_name")
    String playlist_name;
    /*
     * Full args constructor
     * @param playlist_id
     * @param user_id
     * @param playlist_name
     */
    public Playlist(Integer playlist_id, Integer user_id, String playlist_name){
        this.playlist_id = playlist_id;
        this.user_id = user_id;
        this.playlist_name = playlist_name;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    @Override
    public String toString(){
        return "Playlist" + "playlist id= " + playlist_id +
                ", playlist name = " + playlist_name + '\'' +'}';
    }
}
