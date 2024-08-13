package com.example.crud_operation.controller;


import com.example.crud_operation.dto.DTO;
import com.example.crud_operation.entity.FileEntity;
import com.example.crud_operation.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/hotel/customers")
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





    // api for uploading a file
    @PostMapping("/file/upload/{recordId}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @PathVariable Long recordId) throws Exception {
        hotelService.saveFile(file, recordId);
        return ResponseEntity.ok("File uploaded successfully!");
    }


    // api for downloading file
    @GetMapping("/file/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        FileEntity fileEntity = hotelService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                .body(fileEntity.getData());
    }


    //api for downloading a pdf having users data in pdf
    @GetMapping("/download/pdf")
    public ResponseEntity<InputStreamResource> downloadPdf() {
        ByteArrayInputStream pdfStream = hotelService.generatePdf();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=users.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }


    //api for downloading a pdf having users data in excel
    @GetMapping("/download/excel")
    public ResponseEntity<InputStreamResource> downloadExcel() {
        ByteArrayInputStream in = hotelService.generateExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=users.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(in));
    }


}
