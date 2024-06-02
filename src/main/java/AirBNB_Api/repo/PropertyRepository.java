package AirBNB_Api.repo;

import AirBNB_Api.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {



    @Query("select p from Property p  join Location l on p.location =l.id where l.locationName =:locationName")
    List<Property> listPropertyByLocationName(@Param("locationName") String locationName);

    @Query("select p from Property p join p.country c where c.countryName = :countryName")
    List<Property> listPropertyByCountryName(@Param("countryName") String countryName);
}