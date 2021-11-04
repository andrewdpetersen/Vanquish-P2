package Application.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "tracks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Track {
    @Id
    private Integer track_id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

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

    public Integer getId() {
        return track_id;
    }

    public void setId(Integer id) {
        this.track_id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + track_id +
                ", title='" + title + '\'' +
                '}';
    }
}
