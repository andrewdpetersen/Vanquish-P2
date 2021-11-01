package Models;

import javax.persistence.*;

/**
 * This class is used to declare the POJO, Track
 * @date 11/01/2021
 * @author
 */

@Table(name = "TRACKS")
@Entity
public class Track {
    public Track() {
    }

    @Id
    @Column(name = "TRACK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
}
