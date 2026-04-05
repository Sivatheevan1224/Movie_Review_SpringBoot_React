package com.siva.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(String reviewBody, String imdbId){
        Review review = new Review(reviewBody);
         
        return reviewRepository.save(review);
    }
}
