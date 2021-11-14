package Application.services;

import Application.models.Location;
import Application.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Location getLocationById(Integer id){
        return locationRepository.getById(id);
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
