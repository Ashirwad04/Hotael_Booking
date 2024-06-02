package AirBNB_Api.controller;


import AirBNB_Api.entity.AppUser;
import AirBNB_Api.entity.Property;
import AirBNB_Api.entity.Review;
import AirBNB_Api.repo.PropertyRepository;
import AirBNB_Api.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {


    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewController(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping
    public ResponseEntity<String> addReview(
            @AuthenticationPrincipal AppUser user,
            @RequestParam long propertyId,
            @RequestBody Review review

    ){
        Optional<Property> byId = propertyRepository.findById(propertyId);
        Property property = byId.get();

        review.setAppUser(user);
        review.setProperty(property);
        Review r = reviewRepository.fetchUserReview(property, user);
        if (r!=null){
            return new ResponseEntity<>("Review is already given for this property", HttpStatus.BAD_REQUEST);
        }
        reviewRepository.save(review);
        return new ResponseEntity<>("Review added", HttpStatus.CREATED);
    }
}















