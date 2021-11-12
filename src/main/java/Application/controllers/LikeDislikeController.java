package Application.controllers;

import Application.models.Track;
import Application.models.User;
import Application.services.LikeDislikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/4TheMusic")
public class LikeDislikeController {
    private final LikeDislikeService likeDislikeService;

    @Autowired
    public LikeDislikeController(LikeDislikeService likeDislikeService) {
        this.likeDislikeService = likeDislikeService;
    }

    @PostMapping(value = "/like", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Track likeTrack(@RequestBody Track track){
        //get user from token that comes in through the authentication header
        User user = new User();
        //what really needs to happen
        //User user = likeService.getUser(id or username);

        //if track exists on our database already, grab it from there to update
        Track database = likeDislikeService.getTrack(track.getTrack_id());
        if(database != null)
        {
            //add user to track's list of users who like it
            database.getUserLikes().add(user);
            likeDislikeService.saveTrack(database);
        }
        else //if it doesn't exist on our database already, we need to create it
        {
            //create new track with user on the list of users who like it
            track.getUserLikes().add(user);
            likeDislikeService.saveTrack(track);
        }

        //add track to user's liked list after either creating a new track or updating current one
        user.getLiked_tracks().add(likeDislikeService.getTrack(track.getTrack_id()));
        likeDislikeService.saveUser(user);

        return likeDislikeService.getTrack(track.getTrack_id());
    }

    @PostMapping(value = "/dislike", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Track dislikeTrack(@RequestBody Track track){
        //get user from token that comes in through the authentication header
        User user = new User();
        //what really needs to happen
        //User user = LikeDislikeService.getUser(id or username);

        //if track exists on our database already, grab it from there to update
        Track database = likeDislikeService.getTrack(track.getTrack_id());
        if(database != null)
        {
            //add user to track's list of users who like it
            database.getUserDislikes().add(user);
            likeDislikeService.saveTrack(database);
        }
        else //if it doesn't exist on our database already, we need to create it
        {
            //create new track with user on the list of users who like it
            track.getUserDislikes().add(user);
            likeDislikeService.saveTrack(track);
        }

        //add track to user's liked list after either creating a new track or updating current one
        user.getDisliked_tracks().add(track);
        likeDislikeService.saveUser(user);

        return likeDislikeService.getTrack(track.getTrack_id());
    }

    @GetMapping(value="/track/ratio", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value=HttpStatus.OK)
    public String likeDislikeRatio(@RequestBody Track track)
    {
        //get track id from the track that comes in
        Integer id = track.getTrack_id();

        //make sure it exists in our database, if it doesn't there are no likes or dislikes on it.
        Track currentTrack = likeDislikeService.getTrack(id);
        if(currentTrack == null)
        {
            //there is no like/dislike ratio because the track is not persisted in our database and therefore has no likes/dislikes
            return "0/0";
        }
        else
        {
            //count how many users are on both lists and then build a string that displays those numbers for the premium user
            int trackLikes = currentTrack.getUserLikes().size();
            int trackDislikes = currentTrack.getUserDislikes().size();
            return "Likes: " + trackLikes + " Dislikes: " + trackDislikes;
        }
    }
}
