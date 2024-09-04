package com.example.HotelBooking.Service;

import com.example.HotelBooking.Entity.Booking;
import com.example.HotelBooking.Entity.Payment;
import com.example.HotelBooking.Repository.BookingRepository;
import com.example.HotelBooking.Repository.PaymentRepository;
import com.example.HotelBooking.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;



    @Autowired
    private BookingRepository bookingRepository;

    public Payment processPayment(Long bookingId, Payment paymentDetails) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        paymentDetails.setBooking(booking);
        paymentDetails.setPaymentDate(new Date());
        paymentDetails.setTransactionId(UUID.randomUUID().toString());

        Payment savedPayment = paymentRepository.save(paymentDetails);


        booking.setPaymentStatus("Completed");
        bookingRepository.save(booking);

        return savedPayment;
    }
}
