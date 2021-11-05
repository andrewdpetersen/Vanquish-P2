package Application.services;
import Application.models.Concert;
import Application.repositories.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List <Concert> getConcertList(){
        return concertRepository.findAll();
    }

    public void delete(Concert concert) {
        concertRepository.delete(concert);
    }
}
