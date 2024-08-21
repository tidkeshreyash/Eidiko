package com.example.HotelBooking.Repository;

import com.example.HotelBooking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByCity(String city);
}
