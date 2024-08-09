package com.example.crud_operation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DTO {
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
