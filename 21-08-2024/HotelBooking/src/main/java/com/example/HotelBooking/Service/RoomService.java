package com.example.HotelBooking.Service;

import com.example.HotelBooking.DTO.RoomDto;
import com.example.HotelBooking.Entity.Room;
import com.example.HotelBooking.Repository.HotelRepository;
import com.example.HotelBooking.Repository.RoomRepository;
import com.example.HotelBooking.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    // Method to add a room
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Autowired
    private HotelRepository hotelRepository;

    public List<RoomDto> getRoomsByHotel(Long hotelId) {
        // Ensure the hotel exists
        hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + hotelId));

        // Fetch rooms for the specified hotel
        List<Room> rooms = roomRepository.findByHotelId(hotelId);

        // Map to DTO
        return rooms.stream().map(room -> new RoomDto(
                room.getRoomNumber(),
                room.getRoomType(),
                room.getAvailable()
        )).collect(Collectors.toList());
    }
}
