package Application.controllers;
import Application.models.Concert;
import Application.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("music")
public class ConcertController {

    private final ConcertService concertService;

    @Autowired
    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @PostMapping(path = "/concert", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Concert saveConcert(@RequestBody Concert concert){
        concertService.save(concert);
        return concertService.getConcert(concert.getConcert_id());
    }

    @GetMapping(path = "/concert/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List <Concert> concertList(){
        return concertService.getConcertList();
    }

    @GetMapping(path = "/concert/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Concert getConcertById(@PathVariable("id") Integer id){
        return concertService.getConcert(id);
    }

    @DeleteMapping(path = "/concert/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void cancelConcert(@PathVariable("id") Integer id, @RequestBody Concert concert){
       concertService.delete(concert);
    }

    @GetMapping(path = "/concert/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Concert> getConcertByTitle(@PathVariable("name") String name){
    return concertService.getConcertByName(name);
    }

}
