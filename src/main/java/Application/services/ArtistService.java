package Application.services;

import Application.models.Artist;
import Application.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ArtistService
 * Service that manipulates and executes Artist repository methods
 *
 * @date 11/4/2021
 * @author Erika Johnson
 */
@Service
@Transactional
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist save(Artist artist){
        return artistRepository.save(artist);
    }

    public Artist getArtist(Integer id){
        return artistRepository.getById(id);
    }

}