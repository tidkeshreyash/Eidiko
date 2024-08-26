package com.example.HotelBooking.Service;


import com.example.HotelBooking.Entity.*;
import com.example.HotelBooking.Repository.*;
import com.example.HotelBooking.exception.PaymentIncompleteException;
import com.example.HotelBooking.exception.ResourceNotFoundException;
import com.example.HotelBooking.exception.RoomOccupiedException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;




    public Booking createBooking(String location, String type, String hotelName, Booking booking) {

        User user = userRepository.findById(booking.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        Hotel hotel = hotelRepository.findByLocationAndName(location, hotelName)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with name: " + hotelName));

        // Find the room by hotel and room number
        Room room = roomRepository.findByHotelAndRoomNumber(hotel, booking.getRoomNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with number: " + booking.getRoomNumber()));

        // Check if the room is available
        if (room.getAvailable().equals("Room Not Available")) {
            throw new RoomOccupiedException("Room " + booking.getRoomNumber() + " is occupied");
        }

        room.setAvailable("Room Not Available");
        roomRepository.save(room);

        booking.setUser(user);
        booking.setUsername(user.getName());
        booking.setRoom(room);
        booking.setStatus("Booked");

        sendBookingConfirmationEmail(booking);

        return bookingRepository.save(booking);
    }


    private void sendBookingConfirmationEmail(Booking booking) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo("shreyashtidke758@gmail.com");
        message.setSubject("Booking Confirmation - " + booking.getRoom().getHotel().getName());
        message.setText("Dear " + booking.getUsername() + ",\n\n" +
                "Your booking at " + booking.getRoom().getHotel().getName() + " has been confirmed.\n" +
                "Booking Details:\n" +
                "Check-in Date: " + booking.getCheckInDate() + "\n" +
                "Check-out Date: " + booking.getCheckOutDate() + "\n" +
                "Room Number: " + booking.getRoom().getRoomNumber() + "\n\n" +
                "Thank you for choosing us!\n\n" +
                "Best regards,\n" +
                booking.getRoom().getHotel().getName() + " Team");

        mailSender.send(message);
    }






    public Booking updateBookingDates(Long bookingId, Long userId, Booking bookingDetails) {
        // Fetch the existing booking
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        if (!existingBooking.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Booking does not belong to user with id: " + userId);
        }

        existingBooking.setCheckInDate(bookingDetails.getCheckInDate());
        existingBooking.setCheckOutDate(bookingDetails.getCheckOutDate());

        return bookingRepository.save(existingBooking);
    }

    public void deleteBooking(Long bookingId, Long userId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        if (!booking.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Booking does not belong to user with id: " + userId);
        }

        Room room = booking.getRoom();

        room.setAvailable("Room Available");
        roomRepository.save(room);

        bookingRepository.deleteById(bookingId);
    }

    public Booking getBookingByI(Long id){
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking is not present"));
    }

    public List<Booking> getAllBookings(){
     return bookingRepository.findAll();
    }

    public ByteArrayInputStream generateBookingReceipt(Long bookingId) throws DocumentException, IOException{
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking Not Found"));

        Payment payment = paymentRepository.findByBooking(booking)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Not Found"));

        if("Pending".equalsIgnoreCase(payment.getPaymentStatus())){
            throw new PaymentIncompleteException("Payment is not Completed. Do the Payment First");
        }

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document,out);

        document.open();
        Font font = new Font(Font.FontFamily.TIMES_ROMAN,12);

        document.add(new Paragraph("Booking Receipt",font));
        document.add(new Paragraph("Booking Id: " + booking.getId(),font));
        document.add(new Paragraph("Customer Name: " + booking.getUsername(),font));
        document.add(new Paragraph("Check-in Date :"+ booking.getCheckInDate(),font));
        document.add(new Paragraph("Check-out Date: " + booking.getCheckOutDate(), font));
        document.add(new Paragraph("Room Number: " + booking.getRoom().getRoomNumber(), font));
        document.add(new Paragraph("Hotel Name: " + booking.getRoom().getHotel().getName(), font));
        document.add(new Paragraph("Payment Method: " + payment.getPaymentMethod(), font));
        document.add(new Paragraph("Payment Status: " + payment.getPaymentStatus(), font));

        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

}


