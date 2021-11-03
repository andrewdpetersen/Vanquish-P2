package VanquishP2.Repos;

import VanquishP2.Beans.Models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Optional<Album> getAlbumByName(String name);
    Optional<Album> getAlbumByArtist(String artist);
    Optional<Album> findByID(Integer id);
}