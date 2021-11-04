package VanquishP2.Application.Beans.Models;

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

@Table(name = "ALBUMS")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
