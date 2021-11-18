package Application.controllers;

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

import java.util.List;

/**
 * PlaylistController
 * Handles request that involve the manipulating or retrieval of playlist data
 *
 * @date 11/1/2021
 * @author Mike Eads and Andrew Peterson
 */
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

    /**
     * Saves playlist object
     * @param playlist Playlist object ot save
     * @param username Username to get playlist from
     * @return Persisted playlist
     */
    @PostMapping(value = "/playlist/{username}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Playlist savePlaylist_name(@RequestBody Playlist playlist,@PathVariable ("username") String username){
        List<UserInfo> infoList = userInfoService.getAll();

        for (UserInfo info:infoList) {
            if(info.getUsername().equals(username)){
                playlist.setUser(info.getUser());
            }
        }

        return playlistService.savePlaylist(playlist);
    }

    /**
     * Retrieve playlist based on given ID
     * @param id ID to query with
     * @return Retrieved playlist
     */
    @GetMapping(value = "/playlist/{playlist_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Playlist getPlaylistById(@PathVariable("playlist_id") Integer id){
        Playlist respPlaylist = new Playlist();

        respPlaylist.setID(id);
        respPlaylist.setPlaylistName(playlistService.getPlaylistName(id));
        respPlaylist.setUser(userService.getByID(playlistService.getUserId(id)));
        respPlaylist.setTrackList(playlistService.getTracksByPlaylist(id));

        return respPlaylist;
    }

    /**
     * Retrieve all playlists that a User has created
     * @param username Username to query with
     * @return All playlists tied to this user
     */
    @GetMapping(value = "/playlist/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Playlist> getPlaylistsByUser(@PathVariable ("username") String username){
        List<User> userList= userService.getAllUsers();
        User trueUser = null;

        for (User user:userList) {
            UserInfo info = user.getUserInfo();
            if(info.getUsername().equals(username)){
                trueUser = user;
            }
        }

        return playlistService.getPlaylistByUser(trueUser);
    }

    /**
     * Retrieve all tracks with a playlist
     * @param id PlayList ID to query by
     * @return Playlist tracks
     */
    @GetMapping(value = "/playlist/tracks/{playlist_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Track> getTracksFromPlaylist(@PathVariable ("playlist_id") Integer id){
        return playlistService.getTracksByPlaylist(id);
    }
}
