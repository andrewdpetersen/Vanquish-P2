package Application.services;

import Application.models.Track;
import Application.models.User;
import Application.models.UserInfo;
import Application.repositories.TrackRepository;
import Application.repositories.UserInfoRepository;
import Application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LikeDislikeService {
    private final TrackRepository trackRepository;
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public LikeDislikeService(TrackRepository trackRepository, UserRepository userRepository, UserInfoRepository userInfoRepository){
        this.trackRepository = trackRepository;
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
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

    public Optional<User> getUserByUsername(String username)
    {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findByUsername(username);
        if(optionalUserInfo.isPresent())
        {
            UserInfo currentUserInfo = new UserInfo(optionalUserInfo.get().getLocation(),optionalUserInfo.get().getFirstName(),optionalUserInfo.get().getLastName(),
                    optionalUserInfo.get().getUsername(),optionalUserInfo.get().getPassword(),optionalUserInfo.get().getEmail(),optionalUserInfo.get().getUser());
            return userRepository.findUserByUserInfo(currentUserInfo);
        }
        else
        {
            //user does not exist exception
        }
        return Optional.empty();
    }
}
