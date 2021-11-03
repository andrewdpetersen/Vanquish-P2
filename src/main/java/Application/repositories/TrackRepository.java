package Application.repositories;

import Application.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

//@NoRepositoryBean
@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {
}
