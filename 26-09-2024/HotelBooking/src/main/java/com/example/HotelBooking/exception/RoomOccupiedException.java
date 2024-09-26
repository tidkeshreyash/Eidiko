package com.example.HotelBooking.exception;

public class RoomOccupiedException extends RuntimeException {
    public RoomOccupiedException(String message) {
        super(message);
    }
}
