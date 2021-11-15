package Application.controllers;

import Application.deezer.JSONStringToModelConverter;
import Application.models.*;
import Application.services.APIClientService;
import Application.models.Playlist;
import Application.models.Track;
import Application.models.User;
import Application.models.UserInfo;
import Application.services.PlaylistService;
import Application.services.UserInfoService;
import Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/4TheMusic")
public class PlaylistController {
    private final PlaylistService playlistService;
    private final UserService userService;
    private final UserInfoService userInfoService;

    @Autowired
    public PlaylistController(PlaylistService playlistService, UserService userService, UserInfoService userInfoService){
        this.playlistService = playlistService;
        this.userService = userService;
        this.userInfoService = userInfoService;
    }

    @PostMapping(value = "/playlist/{username}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Playlist savePlaylist_name(@RequestBody Playlist playlist,@PathVariable ("username") String username){
        List<UserInfo> infoList = userInfoService.getAll();
        for (UserInfo info:infoList) {
            if(info.getUsername().equals(username)){
                playlist.setUser(info.getUser());
            }
        }
        playlistService.savePlaylist(playlist);
        return playlistService.getPlaylist(playlistService.getMaxPlaylistId());
    }

    @GetMapping(value = "/playlist/{playlist_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Playlist getPlaylistById(@PathVariable("playlist_id") Integer id){
        Playlist respPlaylist = new Playlist();
        respPlaylist.setID(id);
        respPlaylist.setPlaylistName(playlistService.getPlaylistName(id));
        respPlaylist.setUser(userService.getByID(playlistService.getUserId(id)));
        System.out.println(respPlaylist.getUser().getUserInfo().getUsername());
        respPlaylist.setTrackList(playlistService.getTracksByPlaylist(id));
        System.out.println(respPlaylist.getTrackList().size());
        return respPlaylist;
    }

    @GetMapping(value = "/playlist/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Playlist> getPlaylistsByUser(@PathVariable ("username") String username){
        List<User> userList= userService.getAllUsers();
        Integer user_id=0;
        User trueUser = null;
        for (User user:userList) {
            UserInfo info = user.getUserInfo();
            if(info.getUsername().equals(username)){
                trueUser = user;
            }
        }
        return playlistService.getPlaylistByUser(trueUser);
    }

    @GetMapping(value = "/playlist/tracks/{playlist_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Track> getTracksFromPlaylist(@PathVariable ("playlist_id") Integer id){
        return playlistService.getTracksByPlaylist(id);
    }
}
