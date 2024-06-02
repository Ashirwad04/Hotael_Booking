package AirBNB_Api.controller;



import AirBNB_Api.entity.Property;
import AirBNB_Api.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ap1/v1/property")
public class PropertyController {


    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

//get all properties
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties(){
        return new ResponseEntity<>(propertyService.getAllProperties(), HttpStatus.OK);
    }

//get property by id
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id){
        return new ResponseEntity<>(propertyService.getPropertyById(id),HttpStatus.OK);
    }

//add property
    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property){
        return new ResponseEntity<>(propertyService.saveProperty(property),HttpStatus.CREATED);

    }
//update property

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property property) {
        property.setId(id);
        return new ResponseEntity<>(propertyService.saveProperty(property), HttpStatus.OK);
    }
//delete property
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deletePropertyById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//get by location
    @GetMapping("/by-location")
    public ResponseEntity<List<Property>> getPropertiesByLocationName(@RequestParam String locationName) {
        return new ResponseEntity<>(propertyService.getPropertiesByLocationName(locationName), HttpStatus.OK);
    }

//get by country

    @GetMapping("/by-country")
    public ResponseEntity<List<Property>> getPropertiesByCountryName(@RequestParam String countryName) {
        return new ResponseEntity<>(propertyService.getPropertiesByCountryName(countryName), HttpStatus.OK);
    }



}
