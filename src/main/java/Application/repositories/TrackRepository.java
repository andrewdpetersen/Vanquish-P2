package Application.repositories;

import Application.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track,Integer> {
}
