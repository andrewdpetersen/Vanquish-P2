package VanquishP2.Controllers;

import VanquishP2.Application.Beans.ModelServices.LikeDislikeService;
import VanquishP2.Application.Beans.Models.Track;
import VanquishP2.Application.Beans.Models.User;
import VanquishP2.Exceptions.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

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
    public Track likeTrack(@RequestBody Track track) throws UserDoesNotExistException {
        track = likeDislikeService.getTrack(track.getTrackID());
        String username = track.getUser().getUserInfo().getUsername();

        Optional<User> user = likeDislikeService.getUserByUsername(username);

        if(user.isPresent())
        {
            Track serviceTrack = likeDislikeService.getTrack(track.getTrackID());
            if(serviceTrack != null)
            {
                serviceTrack.getUserLikes().add(user.get());
                likeDislikeService.saveTrack(serviceTrack);
            }
            else
            {
                track.getUserLikes().add(user.get());
                likeDislikeService.saveTrack(track);
            }

            user.get().getLikedTracks().add(likeDislikeService.getTrack(track.getTrackID()));
            likeDislikeService.saveUser(user.get());
        }

        return likeDislikeService.getTrack(track.getTrackID());
    }

    @PostMapping(value = "/dislike", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Track dislikeTrack(@RequestBody Track track) throws UserDoesNotExistException{
        track = likeDislikeService.getTrack(track.getTrackID());
        String username = track.getUser().getUserInfo().getUsername();

        Optional<User> user = likeDislikeService.getUserByUsername(username);

        if(user.isPresent())
        {
            User presentUser = new User(user.get().getRole(), user.get().getUserInfo());
            Track database = likeDislikeService.getTrack(track.getTrackID());
            if(database != null)
            {
                database.getUserDislikes().add(presentUser);
                likeDislikeService.saveTrack(database);
            }
            else
            {
                track.getUserDislikes().add(presentUser);
                likeDislikeService.saveTrack(track);
            }

            presentUser.getDislikedTracks().add(likeDislikeService.getTrack(track.getTrackID()));
            likeDislikeService.saveUser(presentUser);
        }

        return likeDislikeService.getTrack(track.getTrackID());
    }

    @GetMapping(value="/track/ratio", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value=HttpStatus.OK)
    public String likeDislikeRatio(@RequestBody Track track)
    {
        Integer id = track.getTrackID();

        Track currentTrack = likeDislikeService.getTrack(id);
        if(currentTrack == null)
        {
            return "0/0";
        }
        else
        {
            int trackLikes = currentTrack.getUserLikes().size();
            int trackDislikes = currentTrack.getUserDislikes().size();
            return "Likes: " + trackLikes + " Dislikes: " + trackDislikes;
        }
    }
}
