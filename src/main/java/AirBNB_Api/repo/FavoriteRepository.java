package AirBNB_Api.repo;

import AirBNB_Api.entity.AppUser;
import AirBNB_Api.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {


    @Query("select f from Favorite f where f.appUser = :user")
    List<Favorite> getFavorites(@Param("user") AppUser user);

}