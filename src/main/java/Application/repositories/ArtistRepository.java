package Application.repositories;
import Application.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

<<<<<<< HEAD
    Artist findByName(String name);
}
=======
    //Artist findByName(String name);
}
>>>>>>> bd93c80105452fa6d2fc22b344109e5ed39147a9
