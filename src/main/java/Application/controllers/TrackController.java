package Application.controllers;

import Application.deezer.JSONStringToModelConverter;
import Application.deezer.TrackSearch;
import Application.models.Track;
import Application.services.APIClientService;
import Application.services.TrackService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "track/search/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Track[] searchForTracksByTitle(@PathVariable ("title") String title) throws JSONException {
        List<Track> trackList = TrackSearch.searchTracks(title,5);
        Track[] tracks = new Track[5];
        for (int i=0;i<5;i++) {
            tracks[i] = trackList.get(i);
        }
        return tracks;
    }

    @GetMapping(value = "track/byAlbum/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Track[] getTracksByAlbum(@PathVariable ("id") Integer album_id) throws JSONException {
        String url = "https://api.deezer.com/album/"+album_id+"/tracks";
        String jsonResponse = APIClientService.get(url);
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray data = jsonObject.getJSONArray("data");
        int size = data.length();
        Track[] trackList = new Track[size];
        for(int i=0;i<size;i++){
            JSONObject jsonData = data.getJSONObject(i);
            int id = jsonData.getInt("id");

            String newURL = "https://api.deezer.com/track/" + id;
            String stringJsonTrack = APIClientService.get(newURL);

            Track resultTrack = JSONStringToModelConverter.trackConverter(stringJsonTrack);
            trackList[i] = resultTrack;
        }
        return trackList;

    }
}