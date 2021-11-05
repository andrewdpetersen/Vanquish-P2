package VanquishP2.Application.Beans.Repos;

import VanquishP2.Application.Beans.Models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    void deleteByID(Integer integer);
    void deleteByAlbumTitle(String title);
    Optional<Album> getAlbumByAlbumTitle(String name);
    Optional<Album> findByID(Integer id);
}