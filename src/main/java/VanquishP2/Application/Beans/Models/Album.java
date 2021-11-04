package VanquishP2.Application.Beans.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

@Table(name = "ALBUMS")
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
public class Album implements Serializable {
    public Album() {
        tracks = new ArrayList<>();
    }

    @Id
    @Column(name = "AlbumID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Column(name = "AlbumTitle")
    private String albumTitle;
    public String getAlbumTitle() {
        return albumTitle;
    }
    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    @Column(name = "ReleaseDate")
    private Date date;
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    private Artist artist;
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @OneToMany
    private List<Track> tracks;
    public List<Track> getTracks() {
        return tracks;
    }
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @ManyToMany
    private List<Genre> albumGenres;
    public List<Genre> getAlbumGenres() {
        return albumGenres;
    }
    public void setAlbumGenres(List<Genre> albumGenres) {
        this.albumGenres = albumGenres;
    }

    @Override
    public String toString() {
        return "Album {\n" +
                "ID: " + ID + ",\n" +
                "albumTitle: " + albumTitle + ",\n" +
                "date: " + date + ",\n" +
                "artist: " + artist + ",\n" +
                "tracks: " + tracks + ",\n" +
                "albumGenres: " + albumGenres + ",\n" +
                '}';
    }
}
