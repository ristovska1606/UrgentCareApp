package mk.ukim.finki.dashw.repository;

import mk.ukim.finki.dashw.model.Rating;
import mk.ukim.finki.dashw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface RatingRepository  extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUser(User user);
}
