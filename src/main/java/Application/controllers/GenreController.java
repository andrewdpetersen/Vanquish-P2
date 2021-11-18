package Application.controllers;

import Application.models.Genre;
import Application.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * GenreController
 *
 *
 * @date 11/1/2021
 * @author
 */
@RestController
@RequestMapping(value = "/4TheMusic")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService)
    {
        this.genreService = genreService;
    }

    /**
     *
     * @param genre
     * @return
     */
    @PostMapping(value = "/genre", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Genre saveGenre(@RequestBody Genre genre){
        System.out.println("POST");
        genreService.save(genre);
        return genreService.getGenre(genre.getGenreID());
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/genre/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Genre getGenre(@PathVariable ("id") Integer id){
        System.out.println("GET");
        return genreService.getGenre(id);
    }

}
