package Application.service;

import Application.models.Playlist;
import Application.models.Track;
import Application.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void savePlaylist(Playlist playlist){
        playlistRepository.save(playlist);
    }

    public Playlist getPlaylist(Integer id){
        return playlistRepository.getById(id);
    }
}
