package Application.controllers;

import Application.deezer.TrackSearch;
import Application.models.Track;
import Application.models.User;
import Application.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/4TheMusic")
public class TrackController {
    private final TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping(value = "/track", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Track saveTrack(@RequestBody Track track){
        trackService.save(track);
        return trackService.getTrack(track.getTrack_id());
    }

    @GetMapping(value = "/track/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@GetMapping(value = "/track?id=x", produces = MediaType.APPLICATION_JSON_VALUE) -> @RequestParam
    @ResponseStatus(value = HttpStatus.OK)
    public Track getTrackById(@PathVariable ("id") Integer id){
        return trackService.getTrack(id);
    }

    @GetMapping(value = "search/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Track[] searchForTracksByTitle(@PathVariable ("title") String title){
        List<Track> trackList = TrackSearch.searchTracks(title,5);
        Track[] tracks = new Track[5];
        for (int i=0;i<5;i++) {
            tracks[i] = trackList.get(i);
        }
        return tracks;
    }
}