package VanquishP2.Controllers;

import VanquishP2.Application.Beans.Models.Album;
import VanquishP2.Application.Beans.ModelServices.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * AlbumController
 * Handles requests that involve the manipulating or retrieval of album data
 *
 * @Date 11/3/2021
 * @Author Kollier Martin
 */

@RestController
@RequestMapping(value = "/album", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("")
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> allAlbums = albumService.getAll();

        if (allAlbums == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (allAlbums.isEmpty())
            return new ResponseEntity<>(allAlbums, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(allAlbums, HttpStatus.OK);
    }

    /**
     * Receives ID from request, then returns an Album object if they exist
     * @param id ID Integer to distinguish album
     * @return Album Object
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Album retrieve(@PathVariable int id) {
        return albumService.getByID(id);
    }

    /**
     * Receives name from request, then returns an Album object if they exist
     * @param name Name String to distinguish album
     * @return Album Object
     */
    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Album retrieve(@PathVariable String name) {
        return albumService.getByTitle(name);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<?> remove(@PathVariable int id) {
        Optional<Album> album = Optional.ofNullable(albumService.getByID(id));

        if (!album.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            albumService.delete(id);
            return new ResponseEntity<>(album.get(), HttpStatus.NO_CONTENT);
        }
    }
}
