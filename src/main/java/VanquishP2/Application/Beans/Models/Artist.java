package VanquishP2.Application.Beans.Models;

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
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
public class Artist {
    public Artist() {
        tracks = new ArrayList<>();
        albums = new ArrayList<>();
        concerts = new ArrayList<>();
    }

    public Artist(String name) {
        this.name = name;
        tracks = new ArrayList<>();
        albums = new ArrayList<>();
        concerts = new ArrayList<>();
    }

    public Artist(String name, int artistID, String imageURL) {
        this.name = name;
        this.artistID = artistID;
        this.imageURL = imageURL;
    }

    @Id
    @Column(name = "ArtistID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artistID;
    public int getID() {
        return artistID;
    }
    public void setID(int artistID) {
        this.artistID = artistID;
    }

    @Column(name = "Name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Picture")
    private String imageURL;
    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @ManyToMany
    List<Concert> concerts;
    public List<Concert> getConcerts() {
        return concerts;
    }
    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }

    @OneToMany(mappedBy = "artist")
    List<Album> albums;
    public List<Album> getAlbums() {
        return albums;
    }
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

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
                "artistID: " + artistID + ",\n" +
                "name: " + name + ",\n" +
                "imageURL: " + imageURL + ",\n" +
                "concerts: " + concerts + ",\n" +
                "albums: " + albums + ",\n" +
                "tracks: " + tracks + ",\n" +
                '}';
    }
}
