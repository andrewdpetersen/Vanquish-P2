package Application.services;

import Application.models.Playlist;
import Application.models.Track;
import Application.repositories.PlaylistRepository;
import Application.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TrackPlaylistService
 * The middle man, or service, that connects to the persistence layer for everything relating to the like/dislike functionality for tracks.
 *
 * @date 11/9/21
 * @author Michael Reece
 */
@Service
@Transactional
public class TrackPlaylistService {
    private final TrackRepository trackRepository;
    private final PlaylistRepository playlistRepository;

    @Autowired
    public TrackPlaylistService(TrackRepository trackRepository, PlaylistRepository playlistRepository){
        this.trackRepository = trackRepository;
        this.playlistRepository = playlistRepository;
    }

    public void saveTrack(Track track){
        trackRepository.save(track);
    }

    public Track getTrack(Integer id){
        return trackRepository.getById(id);
    }

    public Playlist getPlaylist(Integer id)
    {
        return playlistRepository.getById(id);
    }

    public void savePlaylist(Playlist playlist)
    {
        playlistRepository.save(playlist);
    }

    public void removeTrackFromPlaylist(Integer playlist_id,Integer track_id){
        playlistRepository.removeTrackFromPlaylist(playlist_id,track_id);

    }
}
