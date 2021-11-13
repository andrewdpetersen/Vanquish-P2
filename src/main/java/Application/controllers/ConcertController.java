package Application.controllers;
import Application.models.Concert;
import Application.models.Location;
import Application.services.ConcertService;
import Application.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/4TheMusic")
public class ConcertController {

    private final ConcertService concertService;
    private final LocationService locationService;

    @Autowired
    public ConcertController(ConcertService concertService, LocationService locationService) {
        this.concertService = concertService;
        this.locationService = locationService;
    }

    @PostMapping(path = "/concert", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Concert saveConcert(@RequestBody Concert concert){
        Location loc = concert.getLocation();
        locationService.saveLocation(loc);
        concertService.save(concert);
        return concertService.findByName(concert.getName());
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

    @DeleteMapping(value = "/concert/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLocation(@PathVariable ("id") Integer id){
        if(id>0){
            concertService.deleteConcert(concertService.getConcert(id));
        }else{
            concertService.deleteAllConcerts();
        }
    }
}