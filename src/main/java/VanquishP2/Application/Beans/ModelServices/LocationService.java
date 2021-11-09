package VanquishP2.Application.Beans.ModelServices;


import VanquishP2.Application.Beans.Models.Location;
import VanquishP2.Application.Beans.Repos.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return locationRepository.getById(location.getLocationID());
    }

    // Kollier added this
    public List<Location> getAll(){
        return locationRepository.findAll();
    }

    public Location getLocationById(Integer id){
        return locationRepository.getById(id);
    }

    public void save(Location location) {
        locationRepository.save(location);
    }

    public void deleteLocationById(Integer id){
        locationRepository.delete(locationRepository.getById(id));
    }

    public void deleteLocation(Location location){
        locationRepository.delete(location);
    }

    /**
     * To reset DB data if necessary
     */
    public void deleteAllLocations(){
        locationRepository.deleteAll();
    }
}