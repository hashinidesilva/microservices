package com.hashini.moviecatalogservice.resources;

import com.hashini.moviecatalogservice.models.CatalogItem;
import com.hashini.moviecatalogservice.models.UserRating;
import com.hashini.moviecatalogservice.services.MovieInfo;
import com.hashini.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;
    private final MovieInfo movieInfo;
    private final UserRatingInfo userRatingInfo;

    @Autowired
    public MovieCatalogResource(RestTemplate restTemplate,
                                WebClient.Builder webClientBuilder,
                                MovieInfo movieInfo,
                                UserRatingInfo userRatingInfo) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
        this.movieInfo = movieInfo;
        this.userRatingInfo = userRatingInfo;
    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        UserRating ratings = userRatingInfo.getUserRating(userId);

        return ratings.getUserRating().stream()
                .map(movieInfo::getCatalogItem)
                .collect(Collectors.toList());
    }
}


                    /*
                    Movie movie = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8082/movies/" + rating.getMovieId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();
                    */
