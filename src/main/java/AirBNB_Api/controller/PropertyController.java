package AirBNB_Api.controller;


import AirBNB_Api.entity.Property;
import AirBNB_Api.repo.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ap1/v1/property")
public class PropertyController {

    private PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;

    }


    @GetMapping
    public ResponseEntity<List<Property>> getPropertyListing(
            @RequestParam
            String locationName
    ){
        List<Property> propertiesListing =  propertyRepository.listPropertyByLocationOrCountryName(locationName);
        return new ResponseEntity<>(propertiesListing, HttpStatus.OK);

    }
}
