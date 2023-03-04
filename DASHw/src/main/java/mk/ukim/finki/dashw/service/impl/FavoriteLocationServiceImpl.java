package mk.ukim.finki.dashw.service.impl;

import mk.ukim.finki.dashw.model.FavoriteLocation;
import mk.ukim.finki.dashw.model.User;
import mk.ukim.finki.dashw.model.exceptions.FavoriteLocationAlreadyExistsException;
import mk.ukim.finki.dashw.repository.FavoriteLocationRepository;
import mk.ukim.finki.dashw.service.FavoriteLocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteLocationServiceImpl implements FavoriteLocationService {

    private final FavoriteLocationRepository favoriteLocationRepository;

    public FavoriteLocationServiceImpl(FavoriteLocationRepository favoriteLocationRepository) {
        this.favoriteLocationRepository = favoriteLocationRepository;
    }

    @Override
    public List<FavoriteLocation> findAllByUser(User user) {
        return favoriteLocationRepository.findAllByUserr(user);
    }

    @Override
    public FavoriteLocation create(User user, String location) {
        if(favoriteLocationRepository.findByUserrAndLocation(user, location) == null )
        {
            FavoriteLocation newLocation = new FavoriteLocation(user, location);
            return favoriteLocationRepository.save(newLocation);
        }else {
            throw new FavoriteLocationAlreadyExistsException();
        }
    }
}
