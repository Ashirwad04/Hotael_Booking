package AirBNB_Api.controller;


import AirBNB_Api.entity.Location;
import AirBNB_Api.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

//get all locations
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations(){
        return new ResponseEntity<>(locationService.getAllLocation(), HttpStatus.OK);
    }

//get location by id
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id){
        return new ResponseEntity<>(locationService.getLocationById(id), HttpStatus.OK);
    }

//add location
@PostMapping
public ResponseEntity<Location> createLocation(@RequestBody Location location) {
    return new ResponseEntity<>(locationService.saveLocation(location), HttpStatus.CREATED);
}

//update location
    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id,@RequestBody Location location){
        location.setId(id);
        return new ResponseEntity<>(locationService.saveLocation(location), HttpStatus.OK);
    }
//delete location
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteLocation(@PathVariable Long id){
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
