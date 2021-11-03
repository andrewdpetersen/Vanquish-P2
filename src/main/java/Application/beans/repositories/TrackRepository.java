package Application.beans.repositories;

import Application.beans.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
@NoRepositoryBean
public interface TrackRepository extends JpaRepository<Track,Integer> {
}
