package com.example.HotelBooking.Controller;


import com.example.HotelBooking.DTO.RoomDto;
import com.example.HotelBooking.Entity.Room;
import com.example.HotelBooking.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class RoomController {

    @Autowired
    private RoomService roomService;


    @PostMapping("/addRoom")
    public ResponseEntity<Room> addRoom(@RequestBody Room room){
        Room savedRoom  = roomService.addRoom(room);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    @GetMapping("/{location}/{type}/{hotelId}/rooms")
    public ResponseEntity<List<RoomDto>> getRoomsByHotel(
            @PathVariable String location,
            @PathVariable String type,
            @PathVariable Long hotelId) {

        // Assuming location and type validation can be added if necessary
        List<RoomDto> rooms = roomService.getRoomsByHotel(hotelId);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}
