package com.example.HotelBooking.exception;

public class PaymentIncompleteException extends RuntimeException {
    public PaymentIncompleteException(String message) {
        super(message);
    }
}
