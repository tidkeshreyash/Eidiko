package com.example.HotelBooking.Controller;


import com.example.HotelBooking.Entity.Hotel;
import com.example.HotelBooking.Service.HotelService;
import com.example.HotelBooking.commonResponse.Response;
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
    public ResponseEntity<Response<Hotel>> addHotel(@RequestBody Hotel hotel) {
        Hotel savedHotel = hotelService.addHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.successfulResponse("Hotel added successfully", savedHotel));
    }

    @GetMapping("/locations")
    public ResponseEntity<Response<List<String>>> getAllLocations() {
        List<String> locations = hotelService.getAllLocations();
        return ResponseEntity.ok(Response.successfulResponse("Locations fetched successfully", locations));
    }

    @GetMapping("/{location}/type")
    public ResponseEntity<Response<List<String>>> getHotelTypesByLocation(@PathVariable String location) {
        List<String> hotelTypes = hotelService.getHotelTypesByLocation(location);
        return ResponseEntity.ok(Response.successfulResponse("Hotel types fetched successfully", hotelTypes));
    }

    @GetMapping("/{location}/{type}")
    public ResponseEntity<Response<List<String>>> searchHotelNamesByLocationAndType(@PathVariable String location, @PathVariable String type) {
        List<String> hotelNames = hotelService.searchHotelNamesByLocationAndType(location, type);
        return ResponseEntity.ok(Response.successfulResponse("Hotel names fetched successfully", hotelNames));
    }

}
