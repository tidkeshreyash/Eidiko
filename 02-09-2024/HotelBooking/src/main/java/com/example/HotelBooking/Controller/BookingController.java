package com.example.HotelBooking.Controller;


import com.example.HotelBooking.Entity.Booking;
import com.example.HotelBooking.Service.BookingService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

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

    @DeleteMapping("/{userId}/{bookingId}/deleteBooking")
    public ResponseEntity<String> deleteBooking(
            @PathVariable Long bookingId,
            @PathVariable Long userId) {

        bookingService.deleteBooking(bookingId, userId);
        return new ResponseEntity<>("Booking deleted successfully and room status updated.", HttpStatus.OK);
    }


    @GetMapping("/download/bookingReceipt/{bookingId}")
    public ResponseEntity<byte[]> downloadBookingReceipt(@PathVariable Long bookingId) throws DocumentException, IOException {
        ByteArrayInputStream receiptStream = bookingService.generateBookingReceipt(bookingId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=booking_receipt.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(receiptStream.readAllBytes());
    }

    @GetMapping("/booking/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id){
       return new ResponseEntity<>(bookingService.getBookingByI(id),HttpStatus.OK);
    }

    @GetMapping("/allBookings")
    public ResponseEntity<List<Booking>> getAllBookings(){
        return new ResponseEntity<>(bookingService.getAllBookings(),HttpStatus.OK);
    }
}
