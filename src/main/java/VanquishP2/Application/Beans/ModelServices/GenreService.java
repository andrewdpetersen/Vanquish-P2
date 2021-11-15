package VanquishP2.Application.Beans.ModelServices;

import VanquishP2.Application.Beans.Models.Genre;
import VanquishP2.Application.Beans.Repos.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GenreService {
    private final GenreRepository repo;

    @Autowired
    public GenreService(GenreRepository repo){
        this.repo = repo;
    }

    public void save(Genre genre){
        repo.save(genre);
    }

    public Genre getGenre(Integer id){
        return repo.getById(id);
    }
}