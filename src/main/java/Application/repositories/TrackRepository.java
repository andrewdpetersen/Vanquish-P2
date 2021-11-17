package Application.repositories;

import Application.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {
    @Query(value = "select track_id from tracks where track_id=:id", nativeQuery = true)
    List<Integer> getTracksById(Integer id);

    @Query(value="select user_likes_userid from users_liked_tracks where liked_tracks_track_id=:track_id",nativeQuery = true)
    List<Integer> getLikesByTrackId(Integer track_id);

    @Query(value="select user_dislikes_userid from users_disliked_tracks where disliked_tracks_track_id=:track_id",nativeQuery = true)
    List<Integer> getDislikesByTrackId(Integer track_id);
}
