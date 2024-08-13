package com.example.crud_operation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    @OneToMany(mappedBy = "recordId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> files = new ArrayList<>();

    public HotelManagement(Long recordId, String guestName, String email, String phoneNumber, Integer roomNumber, String roomType, Date checkInDate, Date checkOutDate, Integer totalPrice, String paymentStatus) {
        this.guestName = guestName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
    }
}
