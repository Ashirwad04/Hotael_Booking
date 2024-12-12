package AirBNB_Api.controller;


import AirBNB_Api.entity.Country;
import AirBNB_Api.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.sax.SAXResult;
import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private CountryService countryService;


    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
//get all countries
    @GetMapping
    public ResponseEntity<List<Country>> getAllCOuntries(){
        return new ResponseEntity<>(countryService.getAllCountries(), HttpStatus.OK);
    }
//Get countries by id
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id){
        return new ResponseEntity<>(countryService.getCountryById(id), HttpStatus.OK);
    }
//Save country
    @PostMapping
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        return new ResponseEntity<>(countryService.saveCountry(country), HttpStatus.CREATED);
    }

    //update country
     @PutMapping("/{id}")
     public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country country) {
          country.setId(id);
          return new ResponseEntity<>(countryService.saveCountry(country), HttpStatus.OK);
     }

//delete country
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id){
        countryService.deleteCountry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
