package Application.controllers;

import Application.deezer.JSONStringToModelConverter;
import Application.exceptions.UserDoesNotExistException;
import Application.models.*;
import Application.services.LikeDislikeService;
import Application.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import Application.services.APIClientService;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/4TheMusic")
public class LikeDislikeController {
    private final LikeDislikeService likeDislikeService;
    private final JWTUtil jwtUtil;

    @Autowired
    public LikeDislikeController(JWTUtil jwtUtil, LikeDislikeService likeDislikeService) {
        this.jwtUtil = jwtUtil;
        this.likeDislikeService = likeDislikeService;
    }

    @PostMapping(value = "/like/{currentUser}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Track likeTrack(@RequestBody Track track,@PathVariable String currentUser,HttpServletRequest request){
        //get user from username
        Optional<User> user = likeDislikeService.getUserByUsername(currentUser);

        if(user.isPresent())
        {
            //if track exists on our database already, grab it from there to update
            Track database = likeDislikeService.getTrack(track.getTrack_id());
            if(database != null)
            {
                // public Track(Integer track_id, String title, String duration, List<Playlist> playlists, List<User> userLikes, List<User> userDislikes, Artist artist, Album album)
                Track databaseTrack = new Track(database.getTrack_id(),database.getTitle(),database.getDuration(),
                        database.getPlaylists(),database.getUserLikes(),database.getUserDislikes(),database.getArtist(),
                        database.getAlbum());
                //add user to track's list of users who like it
                databaseTrack.getUserLikes().add(user.get());
                likeDislikeService.saveTrack(databaseTrack);
            }
            else //if it doesn't exist on our database already, we need to create it
            {
                //create new track with user on the list of users who like it
                //create artist object to persist
                Artist artist = new Artist(track.getArtist().getName(),track.getArtist().getID(),track.getArtist().getImage_url());
                likeDislikeService.saveArtist(artist);
                String albumRequest = "https://api.deezer.com/album/" + track.getAlbum().getID();
                String stringJsonAlbum = APIClientService.get(albumRequest);
                Album resultAlbum = JSONStringToModelConverter.albumConverter(stringJsonAlbum);
                Genre genre = new Genre(resultAlbum.getGenre().getGenreID(),resultAlbum.getGenre().getGenreName(),resultAlbum.getGenre().getImageURL());
                likeDislikeService.saveGenre(genre);
                Album album = new Album(track.getAlbum().getID(),track.getAlbum().getAlbum_title(),track.getAlbum().getDate(), artist, genre);
                likeDislikeService.saveAlbum(album);
                Track newTrack = new Track(track.getTrack_id(),track.getTitle(),artist,album);
                //System.out.println(newTrack.getTrack_id());
                Album newAlbum = likeDislikeService.getAlbum(album.getID());
                Artist newArtist = likeDislikeService.getArtist(artist.getID());
                newArtist.getAlbums().add(album);
                likeDislikeService.saveArtist(newArtist);
                likeDislikeService.saveTrack(newTrack);
                newAlbum.getTracks().add(newTrack);
                likeDislikeService.saveAlbum(newAlbum);
                newTrack.getUserLikes().add(user.get());
                likeDislikeService.saveTrack(newTrack);
            }

            //add track to user's liked list after either creating a new track or updating current one
            //we know the track exists because we just persisted it.
            user.get().getLiked_tracks().add(likeDislikeService.getTrack(track.getTrack_id()));
            likeDislikeService.saveUser(user.get());
        }
        else
        {
            //throw userdoesnotexistexception
        }

        return likeDislikeService.getTrack(track.getTrack_id());
    }

    @PostMapping(value = "/dislike/{currentUser}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Track dislikeTrack(@RequestBody Track track,@PathVariable String currentUser,HttpServletRequest request){
        //get user from username
        Optional<User> user = likeDislikeService.getUserByUsername(currentUser);

        if(user.isPresent())
        {
            //if track exists on our database already, grab it from there to update
            Track database = likeDislikeService.getTrack(track.getTrack_id());
            if(database != null)
            {
                // public Track(Integer track_id, String title, String duration, List<Playlist> playlists, List<User> userLikes, List<User> userDislikes, Artist artist, Album album)
                Track databaseTrack = new Track(database.getTrack_id(),database.getTitle(),database.getDuration(),
                        database.getPlaylists(),database.getUserLikes(),database.getUserDislikes(),database.getArtist(),
                        database.getAlbum());
                //add user to track's list of users who like it
                databaseTrack.getUserDislikes().add(user.get());
                likeDislikeService.saveTrack(databaseTrack);
            }
            else //if it doesn't exist on our database already, we need to create it
            {
                //create new track with user on the list of users who like it
                //create artist object to persist
                Artist artist = new Artist(track.getArtist().getName(),track.getArtist().getID(),track.getArtist().getImage_url());
                likeDislikeService.saveArtist(artist);
                String albumRequest = "https://api.deezer.com/album/" + track.getAlbum().getID();
                String stringJsonAlbum = APIClientService.get(albumRequest);
                Album resultAlbum = JSONStringToModelConverter.albumConverter(stringJsonAlbum);
                Genre genre = new Genre(resultAlbum.getGenre().getGenreID(),resultAlbum.getGenre().getGenreName(),resultAlbum.getGenre().getImageURL());
                likeDislikeService.saveGenre(genre);
                Album album = new Album(track.getAlbum().getID(),track.getAlbum().getAlbum_title(),track.getAlbum().getDate(), artist, genre);
                likeDislikeService.saveAlbum(album);
                Track newTrack = new Track(track.getTrack_id(),track.getTitle(),artist,album);
                Album newAlbum = likeDislikeService.getAlbum(album.getID());
                Artist newArtist = likeDislikeService.getArtist(artist.getID());
                newArtist.getAlbums().add(album);
                likeDislikeService.saveArtist(newArtist);
                likeDislikeService.saveTrack(newTrack);
                newAlbum.getTracks().add(newTrack);
                likeDislikeService.saveAlbum(newAlbum);
                newTrack.getUserDislikes().add(user.get());
                likeDislikeService.saveTrack(newTrack);
            }

            //add track to user's disliked list after either creating a new track or updating current one
            //we know the track exists because we just persisted it.
            user.get().getDisliked_tracks().add(likeDislikeService.getTrack(track.getTrack_id()));
            likeDislikeService.saveUser(user.get());
        }
        else
        {
            //throw userdoesnotexistexception
        }

        return likeDislikeService.getTrack(track.getTrack_id());
    }

    @GetMapping(value="/getRatio/{track_id}")
    @ResponseStatus(value=HttpStatus.OK)
    public List<Integer> getRatioByTrackId(@PathVariable ("track_id") Integer track_id){
        Integer likes = likeDislikeService.getTotalLikes(track_id);
        Integer dislikes = likeDislikeService.getTotalDislikes(track_id);
        List<Integer> ratio = new LinkedList<>();
        ratio.add(likes);
        ratio.add(dislikes);
        return ratio;
    }
}
