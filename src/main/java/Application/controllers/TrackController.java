package Application.controllers;

import Application.models.Track;
import Application.service.PlaylistService;
import Application.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vanquish")
public class TrackController {
    private TrackService trackService;
    private PlaylistService playlistService;

    @Autowired
    public TrackController(TrackService trackService, PlaylistService playlistService) {
        this.trackService = trackService;
        this.playlistService = playlistService;
    }

    @PostMapping(value = "/track", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Track saveTrack(@RequestBody Track track){
        trackService.save(track);
        return trackService.getTrack(track.getId());
    }

    @GetMapping(value = "/track/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@GetMapping(value = "/track?id=x", produces = MediaType.APPLICATION_JSON_VALUE) -> @RequestParam
    @ResponseStatus(value = HttpStatus.OK)
    public Track getTrackById(@PathVariable ("id") Integer id){
        return trackService.getTrack(id);
    }

    @PatchMapping(value = "/track/{id}/{playlist_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void addTrackToPlaylist(@PathVariable ("id") Integer id,@PathVariable ("playlist_id") Integer playlist_id){
        Track track = trackService.getTrack(id);
        track.setPlaylist(playlistService.getPlaylist(playlist_id));
        trackService.save(track);
    }
}
