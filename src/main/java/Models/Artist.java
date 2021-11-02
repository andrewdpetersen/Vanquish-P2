package Models;

import javax.persistence.*;
import java.util.List;

@Table(name = "Artists")
@Entity
public class Artist {
    public Artist() {
    }

    public Artist(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Column(name = "name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // One artist, many concerts
    @OneToMany
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
