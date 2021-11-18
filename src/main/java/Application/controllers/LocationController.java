package Application.controllers;

import Application.models.Location;
import Application.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * LocationController
 * Handles request that involve the manipulating or retrieval of location data
 *
 * @author Andrew Peterson
 * @date 11/4/2021
 */
@RestController
@RequestMapping("/4TheMusic")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * Retrieve all locations in database
     * @return List of Location objects
     */
    @GetMapping(value = "/location/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Location> getLocation(){
        return locationService.getAll();
    }

    /**
     * Retrieve location data based on its ID
     * @param id Location ID to query with
     * @return Location object
     */
    @GetMapping(value = "/location/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Location getLocation(@PathVariable ("id") Integer id){
        return locationService.getLocationById(id);
    }

    /**
     * Persist new location to database
     * @param location Location object to save
     * @return Newly persisted object
     */
    @PostMapping(value = "/location", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Location saveLocation(@RequestBody Location location){
        locationService.saveLocation(location);
        return locationService.getLocationById(location.getLocation_id());
    }

    /**
     * Update a location
     * @param location Location object to update
     * @return Updated version of persisted object
     */
    @PutMapping(value = "/location", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Location updateLocation(@RequestBody Location location){
        Location updatedLocation = locationService.getLocationById(location.getLocation_id());
        updatedLocation.setCity(location.getCity());
        updatedLocation.setState(location.getState());
        return locationService.saveLocation(updatedLocation);
    }

    /**
     * Delete a location
     * @param id Location ID that determines what data to delete
     */
    @DeleteMapping(value = "/location/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLocation(@PathVariable ("id") Integer id){
        if(id>0){
            locationService.deleteLocation(locationService.getLocationById(id));
        }else{
            locationService.deleteAllLocations();
        }
    }
}
