package Models;

import javax.persistence.*;

/**
 * This class is used to declare the POJO, Playlist
 * @date 10/29/2021
 * @author
 */

@Table(name = "PLAYLISTS")
@Entity
public class Playlist {
    public Playlist() {

    }

    @Id
    @Column(name = "PLAYLIST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
