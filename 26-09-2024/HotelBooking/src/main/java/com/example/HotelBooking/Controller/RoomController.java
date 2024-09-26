package com.example.HotelBooking.Controller;


import com.example.HotelBooking.DTO.RoomDto;
import com.example.HotelBooking.Entity.Room;
import com.example.HotelBooking.Service.RoomService;
import com.example.HotelBooking.commonResponse.Response;
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
    public ResponseEntity<Response<Room>> addRoom(@RequestBody Room room) {
        Room savedRoom = roomService.addRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.successfulResponse("Room added successfully", savedRoom));
    }

    @GetMapping("/{location}/{type}/{hotelId}/rooms")
    public ResponseEntity<Response<List<RoomDto>>> getRoomsByHotel(
            @PathVariable String location,
            @PathVariable String type,
            @PathVariable Long hotelId) {

        // Assuming location and type validation can be added if necessary
        List<RoomDto> rooms = roomService.getRoomsByHotel(hotelId);
        return ResponseEntity.ok(Response.successfulResponse("Rooms fetched successfully", rooms));
    }

    @DeleteMapping("/deleteRoom/{id}")
    public ResponseEntity<Response<String>> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok(Response.successfulResponse("Room deleted successfully"));
    }
}
