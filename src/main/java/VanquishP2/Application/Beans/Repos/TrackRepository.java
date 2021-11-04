package VanquishP2.Application.Beans.Repos;

import VanquishP2.Application.Beans.Models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {
    Optional<Track> findByID(int ID);
}