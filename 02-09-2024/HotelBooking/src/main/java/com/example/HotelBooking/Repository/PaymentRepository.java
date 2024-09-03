package com.example.HotelBooking.Repository;

import com.example.HotelBooking.Entity.Booking;
import com.example.HotelBooking.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByBooking(Booking booking);
}
