package Application.repositories;
import Application.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Integer> {

    //Optional is a container object which may or may not contain a non-null value.
    Optional<Concert> findByName(String name);
}
