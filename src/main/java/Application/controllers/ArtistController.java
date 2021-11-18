package Application.controllers;

import Application.deezer.ArtistSearch;
import Application.models.Artist;
import Application.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ArtistController
 * Handles request that involve the manipulating or retrieval of artist data
 *
 * @date 11/4/2021
 * @author Erika Johnson
 */
@RestController
@RequestMapping("/4TheMusic")
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    /**
     * Persists artist
     * @param artist Artist object to save
     * @return newly persisted Artist
     */
    @PostMapping(path = "/artist", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Artist saveArtist(@RequestBody Artist artist) {
        return artistService.save(artist);
    }

    /**
     * Retrieve Artist from database
     * @param id ID to query with
     * @return Queried Artist
     */
    @GetMapping(path = "/artist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Artist getArtistById(@PathVariable("id") Integer id) {
        return artistService.getArtist(id);
    }

    /**
     * Retrieve 5 artists from Deezer API call
     * @param title Artist(s) name
     * @return array of artists
     */
    @GetMapping(value = "artist/search/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Artist[] searchForArtistsByName(@PathVariable ("name") String title){
        List<Artist> artistList = ArtistSearch.artistSearch(title,5);
        Artist[] artists = new Artist[5];
        for (int i = 0; i < 5; i++) {
            artists[i] = artistList.get(i);
        }
        return artists;
    }
}

