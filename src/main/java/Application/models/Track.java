package Application.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "tracks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Track {
    @Id
    @Column
    private Integer id;

    @Column
    private String title;

    /**
     * Full args constructor
     * @param id
     * @param title
     */
    public Track(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * No args constructor
     */
    public Track() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
