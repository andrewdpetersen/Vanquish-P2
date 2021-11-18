package Application.services;

import Application.models.Concert;
import Application.repositories.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ConcertService
 * Service that manipulates and executes Concert repository methods
 *
 * @date 11/4/2021
 * @author Erika Johnson
 */
@Service
@Transactional
public class ConcertService {
    private final ConcertRepository concertRepository;

    @Autowired
    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public Concert save(Concert concert){
        return concertRepository.save(concert);
    }

    public Concert getConcert(Integer id){
        return concertRepository.getById(id);
    }

    public List <Concert> getConcertList(){
        return concertRepository.findAll();
    }

    public void deleteConcert(Concert concert) {
        concertRepository.delete(concert);
    }

    public void deleteAllConcerts() {
        concertRepository.deleteAll();
    }

    public Concert findByName(String name){return concertRepository.findByName(name);}
}