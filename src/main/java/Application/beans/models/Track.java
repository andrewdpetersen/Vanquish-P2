package Application.beans.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tracks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Track {
    @Id
    private Integer id;

    @Column(name = "track_title")
    private String title;

    @Column(name = "track_likes")
    private int likes;

//    //many tracks per one album
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Album album;
//
//    //many tracks per one artist
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Artist artist;
//
//    //many tracks per one playlist
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Playlist playlist;

    /**
     * Full args constructor
     * @param id
     * @param title
     * @param likes
     */
    public Track(Integer id, String title, int likes) {
        this.id = id;
        this.title = title;
        this.likes = likes;
    }

    /**
     * No args constructor
     */
    public Track() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

//    public Album getAlbum() {
//        return album;
//    }
//
//    public void setAlbum(Album album) {
//        this.album = album;
//    }
//
//    public Artist getArtist() {
//        return artist;
//    }
//
//    public void setArtist(Artist artist) {
//        this.artist = artist;
//    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
