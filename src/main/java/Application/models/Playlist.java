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
    private Integer id;

    @Column
    private String playlist_name;

    @JsonIgnore
    @OneToMany(mappedBy = "track_id")
    List<Track> trackList;

    public Playlist(Integer id, String playlist_name, List<Track> trackList) {
        this.id = id;
        this.playlist_name = playlist_name;
        this.trackList = trackList;
    }

    public Playlist() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
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
                "id=" + id +
                ", playlist_name='" + playlist_name + '\'' +
                ", trackList=" + trackList +
                '}';
    }
}
