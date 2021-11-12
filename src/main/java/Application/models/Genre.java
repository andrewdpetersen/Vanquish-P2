package Application.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "genres")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Genre {
    public Genre() {
        albumsOfGenre = new ArrayList<>();
    }

    public Genre(Integer genreID, String genreName, String imageURL) {
        albumsOfGenre = new ArrayList<>();
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

    @OneToMany
    private List<Album> albumsOfGenre;
    public List<Album> getAlbumsOfGenre() {
        return albumsOfGenre;
    }
    public void setAlbumsOfGenre(List<Album> albumsOfGenre) {
        this.albumsOfGenre = albumsOfGenre;
    }

    @Override
    public String toString() {
        return "Genre {\n" +
                "genreID: " + genreID + ",\n" +
                "genreName: " + genreName + ",\n" +
                "imageURL: " + imageURL + ",\n" +
                "albumsOfGenre: " + albumsOfGenre + ",\n" +
                '}';
    }
}
