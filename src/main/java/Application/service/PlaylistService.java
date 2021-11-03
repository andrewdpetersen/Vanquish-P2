package Application.service;

import Application.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PlaylistService {
    private PlaylistRepository repo;

    public PlaylistService(PlaylistRepository repo){

        @Autowired
        public PlaylistService(PlaylistRepository repo){
            this.repo = repo;
        }

        public void save(Playlist playList){
            repo.save(playList);
        }
        public Playlist getPlaylist_name (Integer id){

        }
    }
}
