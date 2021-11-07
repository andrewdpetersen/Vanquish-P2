package Application.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to declare the POJO, Album
 * @date 10/29/2021
 * @author Kollier Martin
 */

@Component
@Table(name = "albums")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Album implements Serializable {
    public Album() {
        tracks = new ArrayList<>();
    }

    @Id
    @Column(name = "AlbumID")
    private Integer ID;
    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Column(name = "AlbumTitle")
    private String albumTitle;

    public String getAlbum_title() {
        return albumTitle;
    }

    public void setAlbum_title(String album_title) {
        this.albumTitle = album_title;
    }

    @Column(name = "ReleaseDate")
    private String date;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "track_id")
    private List<Track> tracks;
    public List<Track> getTracks() {
        return tracks;
    }
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @ManyToOne
    @JoinColumn(name= "genre_id")
    private Genre genre;
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    //    @ManyToMany
//    @JsonIgnore
//    private List<Genre> genres_of_album;
//    public List<Genre> getGenres_of_album() {
//        return genres_of_album;
//    }
//    public void setGenres_of_album(List<Genre> genres_of_album) {
//        this.genres_of_album = genres_of_album;
//    }

    @Override
    public String toString() {
        return "Album{" +
                "ID=" + ID +
                ", album_title='" + albumTitle + '\'' +
                ", date=" + date +
                ", artist=" + artist +
                ", tracks=" + tracks +
                '}';
    }
}
