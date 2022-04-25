package com.hashini.movieinfoservice.resources;

import com.hashini.movieinfoservice.models.Movie;
import com.hashini.movieinfoservice.models.MovieSummery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public MovieResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId) {
        MovieSummery movieSummery = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey,
                MovieSummery.class);
        return new Movie(movieId, movieSummery.getTitle(), movieSummery.getOverview());
    }
}
