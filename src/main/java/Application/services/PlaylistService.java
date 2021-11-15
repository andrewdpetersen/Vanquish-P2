package Application.services;

import Application.models.Playlist;
import Application.models.Track;
import Application.models.User;
import Application.models.UserInfo;
import Application.repositories.PlaylistRepository;
import Application.repositories.TrackRepository;
import Application.repositories.UserInfoRepository;
import Application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional; 

@Service
@Transactional
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final TrackRepository trackRepository;
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository,TrackRepository trackRepository, UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.playlistRepository = playlistRepository;
        this.trackRepository = trackRepository;
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
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

    public List<Track> getTracksByPlaylist(Integer playlist_id){
        List<Track> trackList = new LinkedList<>();
        List<Integer> idList = playlistRepository.getTrackIDsByPlaylistId(playlist_id);
        for (Integer id:idList) {
            trackList.add(trackRepository.getById(id));
        }
        return trackList;
    }

    public String getPlaylistName(Integer playlist_id){
        return playlistRepository.getPlaylistName(playlist_id);
    }

    public Integer getUserId(Integer playlist_id){
        return playlistRepository.getUserId(playlist_id);
    }

    public Optional<User> getUserByUsername(String username) {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findByUsername(username);
        if (optionalUserInfo.isPresent()) {
            UserInfo currentUserInfo = new UserInfo(optionalUserInfo.get().getLocation(), optionalUserInfo.get().getFirstName(), optionalUserInfo.get().getLastName(),
                    optionalUserInfo.get().getUsername(), optionalUserInfo.get().getPassword(), optionalUserInfo.get().getEmail(), optionalUserInfo.get().getUser());
            return userRepository.findUserByUserInfo(currentUserInfo);
        } else {
            //user does not exist exception
        }
        return Optional.empty();
    }

    public List<Playlist> getPlaylistByUser(User user) {
        return playlistRepository.getPlaylistsByUser(user);
    }
}
