package Repos;

import Models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Integer> {
    Optional<Concert> findByID(int ID);
}