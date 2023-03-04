package mk.ukim.finki.dashw.service.impl;

import mk.ukim.finki.dashw.model.Rating;
import mk.ukim.finki.dashw.model.User;
import mk.ukim.finki.dashw.model.exceptions.InvalidArgumentException;
import mk.ukim.finki.dashw.model.exceptions.RatingNotFoundException;
import mk.ukim.finki.dashw.repository.RatingRepository;
import mk.ukim.finki.dashw.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating create(User user, Integer number, String comment) {
        Rating editRating = this.ratingRepository.findByUser(user).orElseThrow(() -> new RatingNotFoundException());
        if(number == null && comment == null){
            throw new InvalidArgumentException();
        }
        if (editRating != null) {
            editRating.setUser(user);
            editRating.setNumber(number);
            editRating.setComment(comment);
            this.ratingRepository.save(editRating);
            return editRating;
        } else {
            Rating rating = new Rating(user, number, comment);
            return ratingRepository.save(rating);
        }
    }

    @Override
    public Rating create(User user, String comment) {
        Rating editRating = this.ratingRepository.findByUser(user).orElseThrow(() -> new RatingNotFoundException());
        if (editRating != null) {
            editRating.setUser(user);
            editRating.setComment(comment);

            this.ratingRepository.save(editRating);

            return editRating;
        } else {
            Rating rating = new Rating(user, comment);
            return ratingRepository.save(rating);
        }
    }

    @Override
    public Rating create(User user, Integer number) {
        Rating editRating = this.ratingRepository.findByUser(user).orElseThrow(() -> new RatingNotFoundException());
        if (editRating != null) {
            editRating.setUser(user);
            editRating.setNumber(number);
            this.ratingRepository.save(editRating);
            return editRating;
        } else {
            Rating rating = new Rating(user, number);
            return ratingRepository.save(rating);
        }
    }

    @Override
    public Double calculate() {
        double sum = ratingRepository.findAll().stream().mapToDouble(i -> i.getNumber()).sum();
        return sum / ratingRepository.findAll().size();
    }


}

