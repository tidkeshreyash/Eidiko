package com.example.hotelService.service;

import com.example.hotelService.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel saveHotel(Hotel hotel);
    List<Hotel> getAllHotels();
    Hotel getHotelById(String id);

}
