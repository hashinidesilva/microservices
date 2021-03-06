package com.hashini.moviecatalogservice.services;

import com.hashini.moviecatalogservice.models.Rating;
import com.hashini.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    private final RestTemplate restTemplate;

    @Autowired
    public UserRatingInfo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId,
                UserRating.class);
    }

    public UserRating getFallbackUserRating(String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setUserRating(Arrays.asList(new Rating("0", 0)));
        return userRating;
    }
}
