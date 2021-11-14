package VanquishP2.Application.Beans.ModelServices;

import VanquishP2.Application.Beans.Models.Track;
import VanquishP2.Application.Beans.Repos.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrackService {
    private final TrackRepository repo;

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

    public boolean trackExists(Integer id)
    {
        return repo.existsById(id);
    }
}
