package com.example.HotelBooking.Controller;


import com.example.HotelBooking.Entity.Booking;
import com.example.HotelBooking.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/{location}/{type}/{hotelname}/book")
    public ResponseEntity<Booking> bookRoom(
            @PathVariable String location,
            @PathVariable String type,
            @PathVariable String hotelname,
            @RequestBody Booking booking) {
        System.out.println("Booking request received for location: " + location + ", type: " + type + ", hotel name: " + hotelname);

        Booking createdBooking = bookingService.createBooking(location, type, hotelname, booking);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/{bookingId}/update")
    public ResponseEntity<Booking> updateBookingDates(
            @PathVariable Long bookingId,
            @PathVariable Long userId,
            @RequestBody Booking bookingDetails) {

        Booking updatedBooking = bookingService.updateBookingDates(bookingId, userId, bookingDetails);
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{bookingId}/delete")
    public ResponseEntity<String> deleteBooking(
            @PathVariable Long bookingId,
            @PathVariable Long userId) {

        bookingService.deleteBooking(bookingId, userId);
        return new ResponseEntity<>("Booking deleted successfully and room status updated.", HttpStatus.OK);
    }
}
