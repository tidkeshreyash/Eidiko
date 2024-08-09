package com.example.crud_operation.service.impl;

import com.example.crud_operation.dto.DTO;
import com.example.crud_operation.entity.HotelManagement;
import com.example.crud_operation.exception.ResourceNotFoundException;
import com.example.crud_operation.mapper.Mapper;
import com.example.crud_operation.repository.HotelRepository;
import com.example.crud_operation.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {
    private HotelRepository hotelRepository;

    @Override
    public DTO createCustomer(DTO dto) {

        HotelManagement hotelManagement = Mapper.mapToHotelManagement(dto);
        HotelManagement savedCustomer =  hotelRepository.save(hotelManagement);

        return Mapper.mapToDto(savedCustomer);
    }

    @Override
    public DTO getCustomerById(Long customerId) {
        HotelManagement hotelManagement = hotelRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not present with given id : " + customerId));
        return Mapper.mapToDto(hotelManagement);
    }

    @Override
    public List<DTO> getAllCustomers() {
        List<HotelManagement> hotelManagements =  hotelRepository.findAll();
        return hotelManagements.stream().map((hotelManagement) -> Mapper.mapToDto(hotelManagement))
                .collect(Collectors.toList());
    }

    @Override
    public DTO updateCustomer(Long customerId, DTO updatedCustomer) {
        HotelManagement hotelManagement = hotelRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer is not present wit given ID : " + customerId)
        );

        hotelManagement.setGuestName(updatedCustomer.getGuestName());
        hotelManagement.setEmail(updatedCustomer.getEmail());
        hotelManagement.setPhoneNumber(updatedCustomer.getPhoneNumber());
        hotelManagement.setRoomNumber(updatedCustomer.getRoomNumber());
        hotelManagement.setRoomType(updatedCustomer.getRoomType());
        hotelManagement.setCheckInDate(updatedCustomer.getCheckInDate());
        hotelManagement.setCheckOutDate(updatedCustomer.getCheckOutDate());
        hotelManagement.setTotalPrice(updatedCustomer.getTotalPrice());
        hotelManagement.setPaymentStatus(updatedCustomer.getPaymentStatus());

        HotelManagement updatedCustomerObj = hotelRepository.save(hotelManagement);
        return Mapper.mapToDto(updatedCustomerObj);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        HotelManagement hotelManagement = hotelRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer is not present wit given ID : " + customerId)
        );
        hotelRepository.deleteById(customerId);
    }
}
