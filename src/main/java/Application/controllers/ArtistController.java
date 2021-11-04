package Application.controllers;


import Application.models.Artist;
import Application.models.Concert;
import Application.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("music")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping(path = "/artist", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Artist saveArtist(@RequestBody Artist artist) {
        artistService.save(artist);
        return artistService.getArtist(artist.getID());
    }

    @GetMapping(path = "/artist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Artist getArtist(@PathVariable("id") Integer id) {
        return artistService.getArtist(id);
    }

    @GetMapping(path = "/artist/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Artist getArtistByName(@PathVariable("name") String name){
        return artistService.getArtistByName(name);
    }

}
