package VanquishP2.Beans.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to declare the POJO, Artist
 * @date 10/29/2021
 * @author Kollier Martin
 */

@Table(name = "ARTISTS")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Artist {
    public Artist() {
        tracks = new ArrayList<>();
        albums = new ArrayList<>();
        concerts = new ArrayList<>();
    }

    public Artist(String name, int ID) {
        this.name = name;
        this.ID = ID;
        tracks = new ArrayList<>();
        albums = new ArrayList<>();
        concerts = new ArrayList<>();
    }

    @Id
    @Column(name = "ArtistID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Column(name = "Name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Many artists, many concerts
    @ManyToMany
    List<Concert> concerts;
    public List<Concert> getConcerts() {
        return concerts;
    }
    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }

    // One artist, many albums
    @OneToMany(mappedBy = "artist")
    List<Album> albums;
    public List<Album> getAlbums() {
        return albums;
    }
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    // One artist, many tracks
    @OneToMany
    List<Track> tracks;
    public List<Track> getTracks() {
        return tracks;
    }
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "Artist {\n" +
                "ID: " + ID + "\n" +
                "name: " + name + "\n" +
                '}';
    }
}
