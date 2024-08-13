package com.example.crud_operation.service;

import com.example.crud_operation.dto.DTO;
import com.example.crud_operation.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface HotelService {
    DTO createCustomer(DTO dto);

    DTO getCustomerById(Long customerId);

    List<DTO> getAllCustomers();

    DTO updateCustomer(Long customerId, DTO updatedCustomer);

    void deleteCustomer(Long customerId);

    void saveFile(MultipartFile file, Long recordId) throws Exception;

    FileEntity getFile(Long fileId);

    ByteArrayInputStream generatePdf();

    ByteArrayInputStream generateExcel();
}
