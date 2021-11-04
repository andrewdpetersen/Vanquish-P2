package Application.service;
import Application.models.Concert;
import Application.repositories.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConcertService {
    private final ConcertRepository concertRepository;

    @Autowired
    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public void save(Concert concert){
        concertRepository.save(concert);
    }

    public Concert getConcert(Integer id){
        return concertRepository.getById(id);
    }

    public List <Concert> concertList(){
        return concertRepository.findAll();
    }

    /**
     * Optional is a container object which may or may not contain a non-null value.
     * @param name
     * @return
     */
    public Optional<Concert> getConcertByName(String name){
        return concertRepository.findByName(name);
    }
}
