package com.example.crud_operation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotelManagement")
public class HotelManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    private String guestName;
    private String email;
    private String phoneNumber;
    private Integer roomNumber;
    private String roomType;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer totalPrice;
    private String paymentStatus;
}
