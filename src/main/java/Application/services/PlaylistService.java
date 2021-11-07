package Application.services;

import Application.models.Playlist;
import Application.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlaylistService {
    private PlaylistRepository repo;

    @Autowired
    public PlaylistService(PlaylistRepository repo){
        this.repo = repo;
    }
    public void save(Playlist playlist){
        repo.save(playlist);
    }
    public Playlist getPlaylist_name (Integer playlist_id){
        return repo.getById(playlist_id);
    }
}