package Application.services;

import Application.models.Track;
import Application.models.User;
import Application.repositories.TrackRepository;
import Application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LikeDislikeService {
    private final TrackRepository trackRepository;
    private final UserRepository userRepository;

    @Autowired
    public LikeDislikeService(TrackRepository trackRepository, UserRepository userRepository){
        this.trackRepository = trackRepository;
        this.userRepository = userRepository;
    }

    public void saveTrack(Track track){
        trackRepository.save(track);
    }

    public Track getTrack(Integer id){
        return trackRepository.getById(id);
    }

    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    public User getUser(Integer id)
    {
        return userRepository.getById(id);
    }
}
