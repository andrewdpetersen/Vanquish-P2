package VanquishP2.Controllers;


import VanquishP2.Application.Beans.ModelServices.TrackService;
import VanquishP2.Application.Beans.Models.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
        return trackService.getTrack(track.getTrackID());
    }

    @GetMapping(value = "/track/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Track getTrackById(@PathVariable ("id") Integer id){
        return trackService.getTrack(id);
    }
}