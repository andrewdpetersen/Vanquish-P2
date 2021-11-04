package Application.repositories;
import Application.models.Artist;
import Application.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Integer> {

}
