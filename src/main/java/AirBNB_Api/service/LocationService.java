package AirBNB_Api.service;

import AirBNB_Api.entity.Location;
import AirBNB_Api.repo.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LocationService {

    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;

    }

    //get all location
    public List<Location> getAllLocation(){
        return locationRepository.findAll();
    }

    //get location by id
    public Location getLocationById(Long id){
        return locationRepository.findById(id).orElse(null);
    }
    //save location
    public Location saveLocation(Location location){
        return locationRepository.save(location);
    }
    //delete location
    public void deleteLocation(Long id){
        locationRepository.deleteById(id);
    }
}

