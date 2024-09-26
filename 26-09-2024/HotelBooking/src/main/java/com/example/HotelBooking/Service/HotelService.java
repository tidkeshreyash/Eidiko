package com.example.HotelBooking.Service;


import com.example.HotelBooking.Entity.Hotel;
import com.example.HotelBooking.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel addHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    public List<String> getAllLocations() {
        return hotelRepository.findDistinctLocations();
    }

    public List<String> getHotelTypesByLocation(String location) {
        return hotelRepository.findDistinctTypesByLocation(location);
    }

    public List<String> searchHotelNamesByLocationAndType(String location, String type) {
        return hotelRepository.findHotelNamesByLocationAndType(location, type);
    }
}
