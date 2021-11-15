package Application.services;

import Application.models.Playlist;
import Application.models.Track;
import Application.models.User;
import Application.repositories.PlaylistRepository;
import Application.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Andrew Petersen
 * @date 11/06/21
 */
@Service
@Transactional
public class PlaylistService {
    private PlaylistRepository playlistRepository;
    private TrackRepository trackRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository,TrackRepository trackRepository) {
        this.playlistRepository = playlistRepository;
        this.trackRepository = trackRepository;
    }

    public void savePlaylist(Playlist playlist){
        playlistRepository.save(playlist);
    }

    public Playlist getPlaylist(Integer id){
        return playlistRepository.getById(id);
    }

    public int getMaxPlaylistId(){
        List<Playlist> allPlaylists = playlistRepository.findAll();
        int max = 0;
        for (Playlist playlist:allPlaylists) {
            if(playlist.getPlaylist_id()>max){
                max=playlist.getPlaylist_id();
            }
        }
        return max;
    }

    public List<Track> getTracksByPlaylist(Integer playlist_id){
        List<Track> trackList = new LinkedList<>();
        List<Integer> idList = playlistRepository.getTrackIDsByPlaylistId(playlist_id);
        for (Integer id:idList) {
            trackList.add(trackRepository.getById(id));
        }
        return trackList;
    }

    public String getPlaylistName(Integer playlist_id){
        return playlistRepository.getPlaylistName(playlist_id);
    }

    public Integer getUserId(Integer playlist_id){
        return playlistRepository.getUserId(playlist_id);
    }

    public List<Playlist> getPlaylistByUser(User user) {
        return playlistRepository.getPlaylistsByUser(user);
    }
}
