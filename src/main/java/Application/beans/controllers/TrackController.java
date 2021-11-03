package Application.beans.controllers;

import Application.beans.models.Track;
import Application.beans.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("music")
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
        return trackService.getTrack(track.getId());
    }

    @GetMapping(value = "/track/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@GetMapping(value = "/track?id=x", produces = MediaType.APPLICATION_JSON_VALUE) -> @RequestParam
    @ResponseStatus(value = HttpStatus.OK)
    public Track getTrackById(@PathVariable ("id") Integer id){
        return trackService.getTrack(id);
    }
}
