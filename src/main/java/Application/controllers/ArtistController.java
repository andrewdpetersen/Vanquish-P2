package Application.controllers;
import Application.deezer.ArtistSearch;
import Application.models.Artist;
import Application.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/4TheMusic")
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
    public Artist getArtistById(@PathVariable("id") Integer id) {
        return artistService.getArtist(id);
    }



    
    @GetMapping(value = "artist/search/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Artist[] searchForArtistsByName(@PathVariable ("name") String title){
        System.out.println("Test: Artist");
        List<Artist> artistList = ArtistSearch.artistSearch(title,5);
        Artist[] artists = new Artist[5];
        for (int i=0;i<5;i++) {
            artists[i] = artistList.get(i);
        }
        return artists;
    }
}

