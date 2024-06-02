package AirBNB_Api.service;

import AirBNB_Api.entity.Property;
import AirBNB_Api.repo.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    //get all property
    public List<Property> getAllProperties(){
        return propertyRepository.findAll();
    }


    //get properties by id
    public Property getPropertyById(Long id){
        return propertyRepository.findById(id).orElse(null);
    }



    //save properties
    public Property saveProperty(Property property){
        return propertyRepository.save(property);
    }



    //delete property by id
    public void deletePropertyById(Long id){
        propertyRepository.deleteById(id);
    }


// get by Location
    public List<Property> getPropertiesByLocationName(String locationName) {
        return propertyRepository.listPropertyByLocationName(locationName);
    }


//get by Country
    public List<Property> getPropertiesByCountryName(String countryName) {
        return propertyRepository.listPropertyByCountryName(countryName);
    }

}
