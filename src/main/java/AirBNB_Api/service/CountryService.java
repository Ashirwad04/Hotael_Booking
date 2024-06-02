package AirBNB_Api.service;


import AirBNB_Api.entity.Country;
import AirBNB_Api.repo.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private CountryRepository countryRepository;

    private CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    //get all countries
    public List<Country> getAllCountries(){
        return  countryRepository.findAll();
    }
    //get by id
    public Country getCountryById(Long id){
        return countryRepository.findById(id).orElse(null);
    }
    //save country
    public Country saveCountry(Country country){
        return countryRepository.save(country);
    }
    //delete country
    public void deleteCountry(Long id){
        countryRepository.deleteById(id);
    }

}
