package com.example.userService.service.impl;

import com.example.userService.entity.Hotel;
import com.example.userService.entity.Rating;
import com.example.userService.entity.User;
import com.example.userService.exception.ResourceNotFoundException;
import com.example.userService.externalService.HotelService;
import com.example.userService.repository.UserRepository;
import com.example.userService.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;


    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) throws JsonProcessingException {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        logger.info("UserServiceImpl request : {}", new ObjectMapper().writeValueAsString(user));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        logger.info("Got all Users");
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) throws JsonProcessingException {

        User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("not found"));


        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);

        logger.info("{}",ratingOfUser);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);

            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            rating.setHotel(hotel);

            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        logger.info("Got the User : {}", new ObjectMapper().writeValueAsString(user));

        return user;
    }
}
