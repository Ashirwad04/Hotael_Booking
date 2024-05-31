package AirBNB_Api.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.sax.SAXResult;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {


    @PostMapping("/addCountry")
    public String addCountry(){
        return "msg done";
    }
}
