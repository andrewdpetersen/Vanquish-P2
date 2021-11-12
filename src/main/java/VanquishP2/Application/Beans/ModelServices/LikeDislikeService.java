package VanquishP2.Application.Beans.ModelServices;

import VanquishP2.Application.Beans.Models.Track;
import VanquishP2.Application.Beans.Models.User;
import VanquishP2.Application.Beans.Models.UserInfo;
import VanquishP2.Application.Beans.Repos.TrackRepository;
import VanquishP2.Application.Beans.Repos.UserInfoRepository;
import VanquishP2.Application.Beans.Repos.UserRepository;
import VanquishP2.Exceptions.UserDoesNotExistException;
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
    private final String exceptionError = "User: %s does not exist.";

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
        return optionalUserInfo
                .map(userInfo -> userRepository.findUserByUserInfo(userInfo)
                .orElseThrow(() -> new UserDoesNotExistException(String.format(exceptionError, username))));

    }
}