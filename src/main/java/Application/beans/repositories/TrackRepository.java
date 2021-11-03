package Application.beans.repositories;

import Application.beans.models.Track;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;


@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {
}
    /*
    Extending the CrudRepository should implement the following CRUD operations for us:
    save()
    saveAll()
    findById()
    existsById()
    findAll()
    findAllById()
    count()
    deleteById()
    delete()
    deleteAll()
     */

//we can also write our own methods here, for instance JPQL queries that are more
//complex than the options we have above.