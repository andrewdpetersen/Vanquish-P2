package VanquishP2.Application.Beans.ModelServices;

import VanquishP2.Exceptions.AlbumDoesNotExistException;
import VanquishP2.Application.Beans.Models.Album;
import VanquishP2.Application.Beans.Repos.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Album> getAll() {
        return albumRepository.findAll();
    }

    public Album getByID(int id) throws AlbumDoesNotExistException {
        return albumRepository.findByID(id)
                .orElseThrow(AlbumDoesNotExistException::new);
    }

    public Album getByName(String name) throws AlbumDoesNotExistException {
        return albumRepository.getAlbumByName(name)
                .orElseThrow(AlbumDoesNotExistException::new);
    }

    public List<Album> getTwentyAlbums() {
        return albumRepository.findAll().stream().filter(a -> a.getID() <= 20).collect(Collectors.toList());
    }

    public void delete(int id) throws AlbumDoesNotExistException {
        albumRepository.delete(getByID(id));
    }

    public void save(Album album) {
        albumRepository.save(album);
    }
}
