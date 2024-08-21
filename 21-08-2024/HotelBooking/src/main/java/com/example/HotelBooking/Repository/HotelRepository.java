package com.example.HotelBooking.Repository;

import com.example.HotelBooking.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    @Query("SELECT DISTINCT h.location FROM Hotel h")
    List<String> findDistinctLocations();

    @Query("SELECT DISTINCT h.type FROM Hotel h WHERE h.location = :location")
    List<String> findDistinctTypesByLocation(@Param("location") String location);

    @Query("SELECT h.name FROM Hotel h WHERE h.location = :location AND h.type = :type")
    List<String> findHotelNamesByLocationAndType(@Param("location") String location, @Param("type") String type);

    Optional<Hotel> findByLocationAndName(String location, String name);
}
