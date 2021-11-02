package Application.models;

import javax.persistence.*;

@Table(name = "Tracks") //or @Component?
@Entity
public class Track {
    public Track()
    {   }

    @Id
    @Column(name = "Track_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int track_id;

    @Column(name = "Track_Title")
    private String title;

    //possible one if we want to track overall likes?
    @Column(name = "Track_Likes")
    private int likes;

//    //many tracks per one album
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Album album;
//
//    //many tracks per one artist
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Artist artist;

    public int getTrack_id() {
        return track_id;
    }

    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

//    public Album getAlbum() {
//        return album;
//    }
//
//    public void setAlbum(Album album) {
//        this.album = album;
//    }
//
//    public Artist getArtist() {
//        return artist;
//    }
//
//    public void setArtist(Artist artist) {
//        this.artist = artist;
//    }
}
