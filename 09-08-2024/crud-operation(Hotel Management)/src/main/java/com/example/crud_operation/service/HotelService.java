package com.example.crud_operation.service;

import com.example.crud_operation.dto.DTO;

import java.util.List;

public interface HotelService {
    DTO createCustomer(DTO dto);

    DTO getCustomerById(Long customerId);

    List<DTO> getAllCustomers();

    DTO updateCustomer(Long customerId, DTO updatedCustomer);

    void deleteCustomer(Long customerId);
}
