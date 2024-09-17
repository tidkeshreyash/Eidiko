package com.example.ratingService.service.impl;

import com.example.ratingService.entity.Rating;
import com.example.ratingService.exception.ResourceNotFoundException;
import com.example.ratingService.repository.RatingRepository;
import com.example.ratingService.service.RatingService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    private Logger logger = LoggerFactory.getLogger(RatingService.class);

    @Override
    public Rating createRating(Rating rating) {
        String randomUserId = UUID.randomUUID().toString();
        rating.setRatingId(randomUserId);
        return ratingRepository.save(rating);
    }

    @Override
    public Rating getRatingById(String ratingId) {
        return ratingRepository.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found with id: " + ratingId));
    }

    @Override
    public List<Rating> getAllRatings() {
        logger.info("All Ratings : {}");
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        logger.info("Get User Ratings");
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
