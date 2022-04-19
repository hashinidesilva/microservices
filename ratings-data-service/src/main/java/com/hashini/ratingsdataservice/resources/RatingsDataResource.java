package com.hashini.ratingsdataservice.resources;

import com.hashini.ratingsdataservice.models.Rating;
import com.hashini.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId, 2);
    }

    @GetMapping("users/{userId}")
    public UserRating getUserRatings(@PathVariable String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 2),
                new Rating("5678", 2)
        );
        return new UserRating(ratings);
    }

}