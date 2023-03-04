package mk.ukim.finki.dashw.service;

import mk.ukim.finki.dashw.model.FavoriteLocation;
import mk.ukim.finki.dashw.model.User;

import java.util.List;

public interface FavoriteLocationService {
    List<FavoriteLocation> findAllByUser(User user);
    FavoriteLocation create(User user, String location);
}
