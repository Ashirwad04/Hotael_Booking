package AirBNB_Api.repo;

import AirBNB_Api.entity.AppUser;
import AirBNB_Api.entity.Property;
import AirBNB_Api.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.property=:property and r.appUser=:user")
    Review fetchUserReview(@Param("property") Property property,@Param("user") AppUser user);

}