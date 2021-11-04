package Application.controllers;

import Application.beans.models.Concert;
import Application.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("music")
public class ConcertController {

    private final ConcertService concertService;

    @Autowired
    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @PostMapping(path = "/concert")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Concert saveConcert(@RequestBody Concert concert){
        concertService.save(concert);
        return concertService.getConcert(concert.getConcert_id());
    }

    @GetMapping(path = "/concert/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Concert getConcertById(@PathVariable("id") Integer id){
        return concertService.getConcert(id);
    }

    @GetMapping(path = "/concert/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Concert> getConcertByTitle(@PathVariable("name") String name){
    return concertService.getConcertByName(name);
    }
}
