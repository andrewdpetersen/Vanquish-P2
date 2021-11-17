package Application.controllers;

import Application.deezer.JSONStringToModelConverter;
import Application.exceptions.UserDoesNotExistException;
import Application.models.*;
import Application.services.APIClientService;
import Application.services.LikeDislikeService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * LikeDislikeController
 * Handles requests that involve the manipulating or retrieval of tracks in regards to like/dislike
 * @date 11/09/21
 * @author Michael Reece
 */
@RestController
@RequestMapping(value = "/4TheMusic")
public class LikeDislikeController {
    private final LikeDislikeService likeDislikeService;
    private final String errMessage = "User with name %s does not exist!";

    @Autowired
    public LikeDislikeController(LikeDislikeService likeDislikeService) {
        this.likeDislikeService = likeDislikeService;
    }

    /**
     * Like a track for the current user
     * @param track from the RequestBody
     * @param currentUser from the path variable
     * @return the track after a successful 'like'
     */
    @PostMapping(value = "/like/{currentUser}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Track likeTrack(@RequestBody Track track, @PathVariable String currentUser) throws JSONException {
        Optional<User> user = likeDislikeService.getUserByUsername(currentUser);

        if(user.isPresent())
        {
            Track database = likeDislikeService.getTrack(track.getTrack_id());
            if(database != null) {
                Track databaseTrack = new Track(database.getTrack_id(),database.getTitle(),database.getDuration(),
                        database.getPlaylists(),database.getUserLikes(),database.getUserDislikes(),database.getArtist(),
                        database.getAlbum());

                databaseTrack.getUserLikes().add(user.get());
                likeDislikeService.saveTrack(databaseTrack);
            } else {
                createNewArtist(track, user);
            }

            user.get().getLiked_tracks().add(likeDislikeService.getTrack(track.getTrack_id()));
            likeDislikeService.saveUser(user.get());
        }
        else
        {
            throw new UserDoesNotExistException(String.format(errMessage, currentUser));
        }

        return likeDislikeService.getTrack(track.getTrack_id());
    }

    /**
     * Dislike a track for the current user
     * @param track from the RequestBody
     * @param currentUser from the path variable
     * @return the track after a successful 'dislike'
     */
    @PostMapping(value = "/dislike/{currentUser}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Track dislikeTrack(@RequestBody Track track, @PathVariable String currentUser) throws JSONException {
        Optional<User> user = likeDislikeService.getUserByUsername(currentUser);

        if(user.isPresent())
        {
            Track database = likeDislikeService.getTrack(track.getTrack_id());
            if(database != null)
            {
                Track databaseTrack = new Track(database.getTrack_id(),database.getTitle(),database.getDuration(),
                        database.getPlaylists(),database.getUserLikes(),database.getUserDislikes(),database.getArtist(),
                        database.getAlbum());

                databaseTrack.getUserDislikes().add(user.get());
                likeDislikeService.saveTrack(databaseTrack);
            }
            else {
                createNewArtist(track, user);
            }

            user.get().getDisliked_tracks().add(likeDislikeService.getTrack(track.getTrack_id()));
            likeDislikeService.saveUser(user.get());
        }
        else
        {
            throw new UserDoesNotExistException(String.format(errMessage, currentUser));
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

    private void createNewArtist(Track track, Optional<User> user) throws JSONException {
        String albumRequest = "https://api.deezer.com/album/" + track.getAlbum().getID();
        String stringJsonAlbum = APIClientService.get(albumRequest);

        Artist artist = new Artist(track.getArtist().getName(), track.getArtist().getID(), track.getArtist().getImage_url());
        likeDislikeService.saveArtist(artist);

        Album resultAlbum = JSONStringToModelConverter.albumConverter(stringJsonAlbum);
        Genre genre = new Genre(resultAlbum.getGenre().getGenreID(),resultAlbum.getGenre().getGenreName(),resultAlbum.getGenre().getImageURL());
        likeDislikeService.saveGenre(genre);

        Album album = new Album(track.getAlbum().getID(),track.getAlbum().getAlbum_title(),track.getAlbum().getDate(), artist, genre);
        likeDislikeService.saveAlbum(album);

        Track newTrack = new Track(track.getTrack_id(),track.getTitle(), artist,album);

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
}
