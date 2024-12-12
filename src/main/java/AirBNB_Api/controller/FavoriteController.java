package AirBNB_Api.controller;


import AirBNB_Api.entity.AppUser;
import AirBNB_Api.entity.Favorite;
import AirBNB_Api.entity.Property;
import AirBNB_Api.repo.FavoriteRepository;
import AirBNB_Api.repo.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/favorite")
public class FavoriteController {

    private FavoriteRepository fr;
    private PropertyRepository pr;


    public FavoriteController(FavoriteRepository fr, PropertyRepository pr) {
        this.fr = fr;
        this.pr = pr;
    }
    @PostMapping("/addFav")

    public ResponseEntity<Favorite> addFavorite(
            @AuthenticationPrincipal AppUser user,
           @RequestParam long propertyId
    ) {
        Optional<Property> byId = pr.findById(propertyId);
        Property property = byId.get();
        Favorite favorite = new Favorite();
        favorite.setAppUser(user);
        favorite.setProperty(property);
        favorite.setIsFavorite(true);

        Favorite savedFavorite = fr.save(favorite);
        return new ResponseEntity<>(savedFavorite, HttpStatus.CREATED);

    }

    @GetMapping("/userFavoriteList")
    public ResponseEntity<List<Favorite>> getAllFavoritesOfUser(
            @AuthenticationPrincipal AppUser user

    ){
        List<Favorite> favorites = fr.getFavorites(user);
        return new ResponseEntity<>(favorites,HttpStatus.OK);

    }
}
