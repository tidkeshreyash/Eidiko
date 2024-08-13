package com.example.crud_operation.repository;

import com.example.crud_operation.entity.HotelManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository  extends JpaRepository<HotelManagement,Long> {
}
