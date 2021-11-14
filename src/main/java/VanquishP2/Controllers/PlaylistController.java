package VanquishP2.Controllers;

import VanquishP2.Application.Beans.ModelServices.PlaylistService;
import VanquishP2.Application.Beans.Models.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlaylistController {
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @PostMapping(value = "/Playlist_Name", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Playlist savePlaylist_name(@RequestBody Playlist playlist){
        playlistService.save(playlist);
        return playlistService.getPlaylist_name(playlist.getID());
    }

    @GetMapping(value = "/playlist_name/{playlist_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Playlist getPlaylistById(@PathVariable("playlist_id") Integer id){
        return playlistService.getPlaylist_name(id);
    }


}