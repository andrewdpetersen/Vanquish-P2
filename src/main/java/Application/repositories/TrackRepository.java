package Application.repositories;

import Application.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TrackRepository extends JpaRepository<Track,Integer> {
}
