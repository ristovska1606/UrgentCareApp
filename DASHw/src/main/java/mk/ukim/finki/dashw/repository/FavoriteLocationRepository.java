package mk.ukim.finki.dashw.repository;

import mk.ukim.finki.dashw.model.FavoriteLocation;
import mk.ukim.finki.dashw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteLocationRepository extends JpaRepository<FavoriteLocation, Long> {
    List<FavoriteLocation> findAllByUserr(User user);
    FavoriteLocation findByUserrAndLocation(User user, String location);
}
