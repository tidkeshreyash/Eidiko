package com.example.HotelBooking.Service;

import com.example.HotelBooking.Entity.Payment;
import com.example.HotelBooking.Repository.PaymentRepository;
import com.example.HotelBooking.exception.ResourceNotFoundException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ReceiptService {
    @Autowired
    private PaymentRepository paymentRepository;

    public byte[] generateReceipt(Long paymentId) throws IOException, DocumentException {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + paymentId));

        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Add receipt content
        document.add(new Paragraph("Payment Receipt"));
        document.add(new Paragraph("Payment ID: " + payment.getId()));
        document.add(new Paragraph("Payer Name: " + payment.getPayerName()));
        document.add(new Paragraph("Payment Method: " + payment.getPaymentMethod()));
        document.add(new Paragraph("Payment Status: " + payment.getPaymentStatus()));
        document.add(new Paragraph("Booking ID: " + payment.getBooking().getId()));
        document.add(new Paragraph("Hotel: " + payment.getBooking().getRoom().getHotel().getName()));
        document.add(new Paragraph("Room: " + payment.getBooking().getRoom().getRoomNumber()));

        document.close();

        return outputStream.toByteArray();
    }
}
