package Application.controllers;

import Application.models.Playlist;
import Application.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlaylistController {
    private PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @PostMapping(value = "/Playlist_Name", produces = Mediatype.APPLICATION_JSON_VALUE, consumes = MediaType.A)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Playlist savePlaylist_name(@RequestBody Playlist playlist){
        playlistService.save(Playlist);
        return playlistService.getPlaylist_name(playlist.getPlaylist_id());
    }

    @GetMapping(value = "/playlist_name/{playlist_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Playlist getPlaylistById(@PathVariable("id") Integer id){
        return playlistService.getPlaylist_name(id);
    }
}
