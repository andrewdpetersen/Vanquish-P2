package Application.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "concert")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int concert_id;

    @Column(name = "concert_date")
    private String date;

    public Concert() {
    }

    public Concert(int concert_id, String date) {
        this.concert_id = concert_id;
        this.date = date;
    }

    public int getConcert_id() {
        return concert_id;
    }

    public void setConcert_id(int concert_id) {
        this.concert_id = concert_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
                '}';
    }
}
