package Application.repositories;
import Application.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Integer> {
    Concert findByName(String name);
}