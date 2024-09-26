package com.example.HotelBooking.Repository;

import com.example.HotelBooking.Entity.Hotel;
import com.example.HotelBooking.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findByHotelAndAvailable(Hotel hotel, String available);
    Optional<Room> findByHotelAndRoomNumber(Hotel hotel, String roomNumber);
    List<Room> findByHotelId(Long hotelId);
}
