package Application.services;

import Application.models.*;
import Application.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * TrackPlaylistService
 * The middle man, or service, that connects to the persistence layer for everything relating to the like/dislike functionality for tracks.
 *
 * @date 11/9/21
 * @author Michael Reece
 */
@Service
@Transactional
public class LikeDislikeService {
    private final TrackRepository trackRepository;
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public LikeDislikeService(TrackRepository trackRepository, UserRepository userRepository, UserInfoRepository userInfoRepository, ArtistRepository artistRepository, AlbumRepository albumRepository, GenreRepository genreRepository){
        this.trackRepository = trackRepository;
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
    }

    public void saveTrack(Track track){
        trackRepository.save(track);
    }

    public Track getTrack(Integer id){
       List<Integer> getTracks = trackRepository.getTracksById(id);
        if(getTracks.size() != 0)
        {
            return trackRepository.getById(getTracks.get((0)));
        }

        return null;
    }

    public void saveGenre(Genre genre)
    {
        genreRepository.save(genre);
    }

    public Genre getGenre(Integer id)
    {
        return genreRepository.getById(id);
    }

    public void saveArtist(Artist artist)
    {
        artistRepository.save(artist);
    }

    public Artist getArtist(Integer id)
    {
       return artistRepository.getById(id);
    }

    public void saveAlbum(Album album)
    {
        albumRepository.save(album);
    }

    public Album getAlbum(Integer id)
    {
        return albumRepository.getById(id);
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

    public Integer getTotalLikes(Integer track_id){
        return trackRepository.getLikesByTrackId(track_id).size();
    }

    public Integer getTotalDislikes(Integer track_id){
        return trackRepository.getDislikesByTrackId(track_id).size();
    }
}
