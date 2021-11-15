package Application.controllers;

import Application.models.Playlist;
import Application.models.Track;
import Application.services.TrackPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * TrackPlaylistController
 * Handles requests that involve the manipulating or retrieval of playlists, including tracks
 * @date 11/09/21
 * @author Andrew Petersen, Michael Reece
 */
@RestController
@RequestMapping(value = "/4TheMusic")
public class TrackPlaylistController {
    private final TrackPlaylistService trackPlaylistService;

    @Autowired
    public TrackPlaylistController(TrackPlaylistService trackPlaylistService) {
        this.trackPlaylistService = trackPlaylistService;
    }
    
    @PostMapping(value = "/add/{playlist_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Playlist addTrack(@RequestBody Track track, @PathVariable Integer playlist_id){

        //playlist drop down that the user is choosing from is populated from their existing playlists
        //and therefore cannot be null
        Playlist currentPlaylist = trackPlaylistService.getPlaylist(playlist_id);

        //check to see if the track exists in our database already
        Track databaseTrack = trackPlaylistService.getTrack(track.getTrack_id());
        if(databaseTrack != null) //if it exists on our database, no need to add it
        {
            //make sure it isn't in the user's playlist already
            if(currentPlaylist.getTrackList().contains(databaseTrack))
            {
                //throw track already on playlist exception?
            }
            else
            {
                //add playlist to track's playlist list
                databaseTrack.getPlaylists().add(currentPlaylist);
                trackPlaylistService.saveTrack(databaseTrack);
            }
        }
        else
        {
            //make a new track, we know it isn't in the user's playlist already since it hasn't been persisted at all
            track.getPlaylists().add(currentPlaylist);
            trackPlaylistService.saveTrack(track);
        }

        currentPlaylist.getTrackList().add(trackPlaylistService.getTrack(track.getTrack_id()));
        trackPlaylistService.savePlaylist(currentPlaylist);

        return currentPlaylist;
    }

    @DeleteMapping(value="/remove/{playlist_id}/{track_id}")
    @ResponseStatus(value =HttpStatus.OK)
    public void removeTrackFromPlaylist(@PathVariable ("playlist_id") Integer playlist_id, @PathVariable ("track_id") Integer track_id){
        System.out.println("Reached");
        trackPlaylistService.removeTrackFromPlaylist(playlist_id,track_id);
//        trackPlaylistService.savePlaylist(trackPlaylistService.getPlaylist(playlist_id));
//        trackPlaylistService.saveTrack(trackPlaylistService.getTrack(track_id));
        System.out.println("Done");
    }
}