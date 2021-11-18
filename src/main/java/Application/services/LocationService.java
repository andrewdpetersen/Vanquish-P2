package Application.services;

import Application.models.Location;
import Application.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This service bean is used to talk to its designated repository and handle data retrieval for 'Location'
 *
 * @author Andrew Peterson
 * @date 11/6/2021
 */
@Service
@Transactional
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    public Location saveLocation(Location location){
        locationRepository.save(location);
        return locationRepository.getById(location.getLocation_id());
    }

    public List<Location> getAll(){
        return locationRepository.findAll();
    }

    public Location getLocationById(Integer id){
        return locationRepository.getById(id);
    }

    public void save(Location location) {
        locationRepository.save(location);
    }

    public void deleteLocation(Location location){
        locationRepository.delete(location);
    }

    public void deleteAllLocations(){
        locationRepository.deleteAll();
    }
}