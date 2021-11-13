package Application.services;

import Application.models.Playlist;
import Application.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaylistService {
    private PlaylistRepository playlistRepository;

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
}
