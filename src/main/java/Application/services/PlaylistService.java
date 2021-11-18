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
 * This service bean is used to talk to its designated repository and handle data retrieval for 'Playlist'
 *
 * @author Andrew Peterson, Mike Eads, Michael Reece
 * @date 11/06/21
 */
@Service
@Transactional
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final TrackRepository trackRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository,TrackRepository trackRepository) {
        this.playlistRepository = playlistRepository;
        this.trackRepository = trackRepository;
    }

    public Playlist savePlaylist(Playlist playlist){
        return playlistRepository.save(playlist);
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
