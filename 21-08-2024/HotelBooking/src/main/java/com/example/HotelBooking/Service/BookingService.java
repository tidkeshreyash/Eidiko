package com.example.HotelBooking.Service;


import com.example.HotelBooking.Entity.Booking;
import com.example.HotelBooking.Entity.Hotel;
import com.example.HotelBooking.Entity.Room;
import com.example.HotelBooking.Entity.User;
import com.example.HotelBooking.Repository.BookingRepository;
import com.example.HotelBooking.Repository.HotelRepository;
import com.example.HotelBooking.Repository.RoomRepository;
import com.example.HotelBooking.Repository.UserRepository;
import com.example.HotelBooking.exception.ResourceNotFoundException;
import com.example.HotelBooking.exception.RoomOccupiedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return bookingRepository.save(booking);
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

}


