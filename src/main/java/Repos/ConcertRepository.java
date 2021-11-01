package Repos;

import Models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConcertRepository extends JpaRepository<Concert, Integer> {
    Optional<Concert> findByID(int ID);
}