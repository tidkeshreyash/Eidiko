package com.example.crud_operation.mapper;

import com.example.crud_operation.dto.DTO;
import com.example.crud_operation.entity.HotelManagement;

public class Mapper {
    public static DTO mapToDto(HotelManagement hotelManagement){
        return new DTO(
                hotelManagement.getRecordId(),
                hotelManagement.getGuestName(),
                hotelManagement.getEmail(),
                hotelManagement.getPhoneNumber(),
                hotelManagement.getRoomNumber(),
                hotelManagement.getRoomType(),
                hotelManagement.getCheckInDate(),
                hotelManagement.getCheckOutDate(),
                hotelManagement.getTotalPrice(),
                hotelManagement.getPaymentStatus()
        );
    }
    public static HotelManagement mapToHotelManagement(DTO dto){
        return new HotelManagement(
                dto.getRecordId(),
                dto.getGuestName(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getRoomNumber(),
                dto.getRoomType(),
                dto.getCheckInDate(),
                dto.getCheckOutDate(),
                dto.getTotalPrice(),
                dto.getPaymentStatus()
        );
    }
}


