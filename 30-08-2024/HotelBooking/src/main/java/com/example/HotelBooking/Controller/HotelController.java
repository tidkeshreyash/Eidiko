package com.example.HotelBooking.Controller;


import com.example.HotelBooking.Entity.Hotel;
import com.example.HotelBooking.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping("/addHotel")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        return new ResponseEntity<>(hotelService.addHotel(hotel), HttpStatus.CREATED);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<String>> getAllLocations() {
        return new ResponseEntity<>(hotelService.getAllLocations(), HttpStatus.OK);
    }


    @GetMapping("/{location}/type")
    public ResponseEntity<List<String>> getHotelTypesByLocation(@PathVariable String location) {
        List<String> hotelTypes = hotelService.getHotelTypesByLocation(location);
        return new ResponseEntity<>(hotelTypes, HttpStatus.OK);
    }

    @GetMapping("/{location}/{type}")
    public ResponseEntity<List<String>> searchHotelNamesByLocationAndType(@PathVariable String location, @PathVariable String type) {
        List<String> hotelNames = hotelService.searchHotelNamesByLocationAndType(location, type);
        return new ResponseEntity<>(hotelNames, HttpStatus.OK);
    }

}
