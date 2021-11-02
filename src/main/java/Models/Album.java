package Models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Table(name = "Albums")
@Entity
public class Album {
    public Album() {
    }

    @Id
    @Column(name = "AlbumID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    @Column(name = "AlbumName")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    Artist artist;
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @OneToMany
    List<Track> tracks;
    public List<Track> getTracks() {
        return tracks;
    }
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
