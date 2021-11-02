package Application.service;

import Application.models.Track;
import Application.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrackService {
    private TrackRepository repo;

    @Autowired
    public TrackService(TrackRepository repo){
        this.repo = repo;
    }

    public void save(Track track){
        repo.save(track);
    }

    public Track getTrack(Integer id){
        return repo.getById(id);
    }
}
