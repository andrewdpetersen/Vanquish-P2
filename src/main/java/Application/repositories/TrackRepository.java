package Application.repositories;

import Application.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {
    @Query(value="select track_id from tracks where track_id=:id",nativeQuery = true)
    List<Integer> getTracksById(Integer id);
}
