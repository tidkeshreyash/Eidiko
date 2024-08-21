package com.example.HotelBooking.Controller;


import com.example.HotelBooking.Entity.Payment;
import com.example.HotelBooking.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/payment/{bookingId}")
    public ResponseEntity<Payment> processPayment(@PathVariable Long bookingId,
                                                  @RequestBody Payment paymentDetails) {
        Payment payment = paymentService.processPayment(bookingId, paymentDetails);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

}
