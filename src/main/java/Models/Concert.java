package Models;

import javax.persistence.*;

/**
 * This class is used to declare the POJO, Concert
 * @date 11/01/2021
 * @author
 */

@Table(name = "CONCERTS")
@Entity
public class Concert {
    public Concert() {
    }

    @Id
    @Column(name = "CONCERT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
}
