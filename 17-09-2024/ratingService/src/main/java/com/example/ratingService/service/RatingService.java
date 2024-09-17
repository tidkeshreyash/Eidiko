package com.example.ratingService.service;

import com.example.ratingService.entity.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(Rating rating);
    Rating getRatingById(String ratingId);
    List<Rating> getAllRatings();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
}
