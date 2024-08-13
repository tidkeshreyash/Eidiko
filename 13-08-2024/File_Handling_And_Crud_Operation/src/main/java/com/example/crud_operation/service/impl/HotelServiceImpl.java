package com.example.crud_operation.service.impl;

import com.example.crud_operation.dto.DTO;
import com.example.crud_operation.entity.FileEntity;
import com.example.crud_operation.entity.HotelManagement;
import com.example.crud_operation.exception.ResourceNotFoundException;
import com.example.crud_operation.mapper.Mapper;
import com.example.crud_operation.repository.FileRepository;
import com.example.crud_operation.repository.HotelRepository;
import com.example.crud_operation.service.HotelService;
import lombok.AllArgsConstructor;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public DTO createCustomer(DTO dto) {

        HotelManagement hotelManagement = Mapper.mapToHotelManagement(dto);
        HotelManagement savedCustomer =  hotelRepository.save(hotelManagement);

        return Mapper.mapToDto(savedCustomer);
    }

    @Override
    public DTO getCustomerById(Long customerId) {
        HotelManagement hotelManagement = hotelRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer is not present with given id : " + customerId));
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
                () -> new ResourceNotFoundException("Customer is not present with given ID : " + customerId)
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




    // method for saving the file into database
    @Override
    public void saveFile(MultipartFile file, Long recordId) throws Exception {
        HotelManagement userId = hotelRepository.findById(recordId)
                .orElseThrow(() -> new Exception("Record not found"));

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(getFileExtension(file.getOriginalFilename()));
        fileEntity.setData(file.getBytes());
        fileEntity.setUploadDateTime(LocalDateTime.now());
        fileEntity.setRecordId(userId);

        fileRepository.save(fileEntity);
    }


    private String getFileExtension(String filename){
        return filename.substring(filename.lastIndexOf(".")+1);
    }


    // method for downloading the file from database
    @Override
    public FileEntity getFile(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id " + id));
    }




    // method for downloading a users data into pdf
    @Override
    public ByteArrayInputStream generatePdf() {
        List<HotelManagement> users = hotelRepository.findAll();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph title = new Paragraph("User Details");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);


            addTableHeader(table, "ID");
            addTableHeader(table, "Guest Name");
            addTableHeader(table, "Email");
            addTableHeader(table, "Phone Number");
            addTableHeader(table, "Room Number");
            addTableHeader(table, "Room Type");
            addTableHeader(table, "Check-In Date");
            addTableHeader(table, "Check-Out Date");


            for (HotelManagement user : users) {
                table.addCell(user.getRecordId() != null ? user.getRecordId().toString() : "N/A");
                table.addCell(user.getGuestName() != null ? user.getGuestName() : "N/A");
                table.addCell(user.getEmail() != null ? user.getEmail() : "N/A");
                table.addCell(user.getPhoneNumber() != null ? user.getPhoneNumber() : "N/A");
                table.addCell(user.getRoomNumber() != null ? user.getRoomNumber().toString() : "N/A");
                table.addCell(user.getRoomType() != null ? user.getRoomType() : "N/A");
                table.addCell(user.getCheckInDate() != null ? user.getCheckInDate().toString() : "N/A");
                table.addCell(user.getCheckOutDate() != null ? user.getCheckOutDate().toString() : "N/A");
            }

            document.add(table);
            document.close();

            return new ByteArrayInputStream(out.toByteArray());
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addTableHeader(PdfPTable table, String headerTitle) {
        PdfPCell header = new PdfPCell();
        header.setPhrase(new Paragraph(headerTitle));
        table.addCell(header);
    }



    // method for downloading a users data into excel
    @Override
    public ByteArrayInputStream generateExcel() {
        List<HotelManagement> users = hotelRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("User Details");


            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Guest Name", "Email", "Phone Number", "Room Number", "Room Type", "Check-In Date", "Check-Out Date"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(createHeaderCellStyle(workbook));
            }

            int rowIndex = 1;
            for (HotelManagement user : users) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(user.getRecordId() != null ? user.getRecordId() : 0);
                row.createCell(1).setCellValue(user.getGuestName() != null ? user.getGuestName() : "N/A");
                row.createCell(2).setCellValue(user.getEmail() != null ? user.getEmail() : "N/A");
                row.createCell(3).setCellValue(user.getPhoneNumber() != null ? user.getPhoneNumber() : "N/A");
                row.createCell(4).setCellValue(user.getRoomNumber() != null ? user.getRoomNumber() : 0);
                row.createCell(5).setCellValue(user.getRoomType() != null ? user.getRoomType() : "N/A");
                row.createCell(6).setCellValue(user.getCheckInDate() != null ? user.getCheckInDate().toString() : "N/A");
                row.createCell(7).setCellValue(user.getCheckOutDate() != null ? user.getCheckOutDate().toString() : "N/A");
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerCellStyle.setFont(font);
        return headerCellStyle;
    }
}




