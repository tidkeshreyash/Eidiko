package com.example.HotelBooking.Controller;


import com.example.HotelBooking.Entity.Booking;
import com.example.HotelBooking.Service.BookingService;
import com.example.HotelBooking.commonResponse.Response;
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
    public ResponseEntity<Response<Booking>> bookRoom(
            @PathVariable String location,
            @PathVariable String type,
            @PathVariable String hotelname,
            @RequestBody Booking booking) {

        Booking createdBooking = bookingService.createBooking(location, type, hotelname, booking);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.successfulResponse("Room booked successfully", createdBooking));
    }

    @PutMapping("/{userId}/{bookingId}/update")
    public ResponseEntity<Response<Booking>> updateBookingDates(
            @PathVariable Long bookingId,
            @PathVariable Long userId,
            @RequestBody Booking bookingDetails) {

        Booking updatedBooking = bookingService.updateBookingDates(bookingId, userId, bookingDetails);
        return ResponseEntity.ok(Response.successfulResponse("Booking dates updated successfully", updatedBooking));
    }

    @DeleteMapping("/{userId}/{bookingId}/deleteBooking")
    public ResponseEntity<Response<String>> deleteBooking(
            @PathVariable Long bookingId,
            @PathVariable Long userId) {

        bookingService.deleteBooking(bookingId, userId);
        return ResponseEntity.ok(Response.successfulResponse("Booking deleted successfully and room status updated."));
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
    public ResponseEntity<Response<Booking>> getBooking(@PathVariable Long id) {
        Booking booking = bookingService.getBookingByI(id);
        return ResponseEntity.ok(Response.successfulResponse("Booking details fetched successfully", booking));
    }

    @GetMapping("/allBookings")
    public ResponseEntity<Response<List<Booking>>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(Response.successfulResponse("All bookings fetched successfully", bookings));
    }
}
