package com.example.HotelBooking.Repository;

import com.example.HotelBooking.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
