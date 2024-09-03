package com.example.HotelBooking.Repository;

import com.example.HotelBooking.Entity.Booking;
import com.example.HotelBooking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByUser(User user);
}
