package VanquishP2.Application.Beans.ModelServices;

import VanquishP2.Application.Beans.Models.Album;
import VanquishP2.Application.Beans.Repos.AlbumRepository;
import VanquishP2.Exceptions.AlbumDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * AlbumService
 * The middle man, or service, that connects to the persistence layer for everything Album related
 *
 * @date 10/29/2021
 * @author Kollier Martin
 */

@Service
@Transactional
public class AlbumService {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    /**
     * Get all Albums
     * @return List of Albums
     */
    public List<Album> getAll() {
        return albumRepository.findAll();
    }

    /**
     * Fetch Album by its ID
     * @param id Album ID
     * @return Album Object
     * @throws AlbumDoesNotExistException Album does not exist in DB
     */
    public Album getByID(int id) throws AlbumDoesNotExistException {
        return albumRepository.findByAlbumID(id)
                .orElseThrow(AlbumDoesNotExistException::new);
    }

    /**
     * Fetch Album by its Title
     * @param title Album Title
     * @return Album Object
     * @throws AlbumDoesNotExistException Album does not exist in DB
     */
    public Album getByTitle(String title) throws AlbumDoesNotExistException {
        return albumRepository.getAlbumByAlbumTitle(title)
                .orElseThrow(AlbumDoesNotExistException::new);
    }

    /**
     * Delete album by ID
     * @param id Album ID
     * @throws AlbumDoesNotExistException Album does not exist in DB
     */
    public void delete(int id) throws AlbumDoesNotExistException {
        albumRepository.delete(getByID(id));
    }

    /**
     * Delete album by album
     * @param album Album object
     * @throws AlbumDoesNotExistException Album does not exist in DB
     */
    public void delete(Optional<Album> album) throws AlbumDoesNotExistException {
        albumRepository.delete(album.get());
    }

    /**
     * This method saves an album to the DB
     * @param album Album object that we'll be saving to the DB
     */
    public void save(Album album) {
        albumRepository.save(album);
    }
}
