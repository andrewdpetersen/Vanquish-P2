package VanquishP2.Application.Beans.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "GENRES")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Genre {
    public Genre() {
        genreOfAlbums = new ArrayList<>();
    }

    public Genre(Integer genreID, String genreName, String imageURL) {
        this.genreID = genreID;
        this.genreName = genreName;
        this.imageURL = imageURL;
    }

    @Id
    @Column
    private Integer genreID;
    public Integer getGenreID() {
        return genreID;
    }
    public void setGenreID(Integer genreID) {
        this.genreID = genreID;
    }

    @Column
    private String genreName;
    public String getGenreName() {
        return genreName;
    }
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Column
    private String imageURL;
    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @ManyToMany(mappedBy = "albumGenres")
    private List<Album> genreOfAlbums;
    public List<Album> getGenreOfAlbums() {
        return genreOfAlbums;
    }
    public void setGenreOfAlbums(List<Album> genreOfAlbums) {
        this.genreOfAlbums = genreOfAlbums;
    }
}