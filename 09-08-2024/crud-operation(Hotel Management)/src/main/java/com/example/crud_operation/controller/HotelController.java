package com.example.crud_operation.controller;


import com.example.crud_operation.dto.DTO;
import com.example.crud_operation.service.HotelService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class HotelController {

    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<DTO> createCustomer(@RequestBody DTO dto){
         DTO savedCustomer = hotelService.createCustomer(dto);
         return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("{recordId}")
    public ResponseEntity<DTO> getCustomerById(@PathVariable("recordId") Long customerId){
        DTO dto = hotelService.getCustomerById(customerId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DTO>> getAllCustomers(){
        List<DTO> customers = hotelService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("{recordId}")
    public ResponseEntity<DTO> updateCustomer(@PathVariable("recordId") Long customerId, @RequestBody DTO updatedCustomer){
        DTO customerdto = hotelService.updateCustomer(customerId, updatedCustomer);
        return ResponseEntity.ok(customerdto);
    }


    @DeleteMapping("{recordId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("recordId") Long customerId){
        hotelService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer deleted Successfully !");
    }
}
