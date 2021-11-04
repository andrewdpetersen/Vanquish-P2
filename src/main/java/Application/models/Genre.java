package Application.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Table(name = "genres")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Genre {

    @Id
    @Column
    private Integer genre_id;

    @Column
    private String genre_name;

    @Column
    private String image_url;

    @ManyToMany(mappedBy = "genres_of_album")
    private List<Album> albums_of_genre;

    public Genre(Integer genre_id, String genre_name, String image_url) {
        this.genre_id = genre_id;
        this.genre_name = genre_name;
        this.image_url = image_url;
    }

    public Genre() {
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genre_id=" + genre_id +
                ", genre_name='" + genre_name + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
