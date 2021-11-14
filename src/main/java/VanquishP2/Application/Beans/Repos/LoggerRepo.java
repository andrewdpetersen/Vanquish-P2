package VanquishP2.Application.Beans.Repos;

import VanquishP2.Application.Beans.Models.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepo extends JpaRepository<Logger, Integer>{

}