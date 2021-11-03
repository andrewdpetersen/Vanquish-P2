package Application.beans.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Entity
@Table(name = "concert")
public class Concert implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer concert_id;

    @Column(name = "concert_date")
    private String date;

    private String name;

    public Concert() {
    }

    public Integer getConcert_id() {
        return concert_id;
    }

    public void setConcert_id(Integer concert_id) {
        this.concert_id = concert_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //    @OneToMany
//    @JoinColumn(name = "concert_state_id", referencedColumnName = "concert_id")
//    private List<Concert> concertList;
//
//    @OneToMany
//    @JoinColumn(name = "concert_artist_id", referencedColumnName = "concert_id")
//    private List<Concert> concertList;


    @Override
    public String toString() {
        return "Concert{" +
                "concert_id=" + concert_id +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
