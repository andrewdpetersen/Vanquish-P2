package Application.controllers;

import Application.exceptions.DuplicateEntryException;
import Application.models.Playlist;
import Application.models.Track;
import Application.services.TrackPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * TrackPlaylistController
 * Handles requests that involve the manipulating or retrieval of playlist tracks
 *
 * @date 11/10/2021
 * @author Andrew Peterson and Michael Reece
 */
@RestController
@RequestMapping(value = "/4TheMusic")
public class TrackPlaylistController {
    private final TrackPlaylistService trackPlaylistService;

    @Autowired
    public TrackPlaylistController(TrackPlaylistService trackPlaylistService) {
        this.trackPlaylistService = trackPlaylistService;
    }

    /**
     * This method adds a track to a playlist
     * @param track Track to add
     * @param playlist_id ID to query for playlist
     * @return Playlist after persisting track
     */
    @PostMapping(value = "/add/{playlist_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Playlist addTrack(@RequestBody Track track, @PathVariable Integer playlist_id){
        Playlist currentPlaylist = trackPlaylistService.getPlaylist(playlist_id);

        Track databaseTrack = trackPlaylistService.getTrack(track.getTrack_id());

        if(databaseTrack != null)
        {
            if(currentPlaylist.getTrackList().contains(databaseTrack))
            {
                throw new DuplicateEntryException("This track is already present on this playlist!");
            }
            else
            {
                databaseTrack.getPlaylists().add(currentPlaylist);
                trackPlaylistService.saveTrack(databaseTrack);
            }
        }
        else
        {
            track.getPlaylists().add(currentPlaylist);
            trackPlaylistService.saveTrack(track);
        }

        currentPlaylist.getTrackList().add(trackPlaylistService.getTrack(track.getTrack_id()));
        trackPlaylistService.savePlaylist(currentPlaylist);

        return currentPlaylist;
    }

    /**
     * This method removes a track from a playlist
     * @param track_id ID to query for track
     * @param playlist_id ID to query for playlist
     */
    @DeleteMapping(value="/remove/{playlist_id}/{track_id}")
    @ResponseStatus(value =HttpStatus.OK)
    public void removeTrackFromPlaylist(@PathVariable ("playlist_id") Integer playlist_id, @PathVariable ("track_id") Integer track_id){
        trackPlaylistService.removeTrackFromPlaylist(playlist_id,track_id);
    }
}