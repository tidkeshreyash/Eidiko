package com.example.HotelBooking.Controller;


import com.example.HotelBooking.Service.ReceiptService;
import com.example.HotelBooking.commonResponse.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/download/{paymentId}")
    public ResponseEntity<Response<byte[]>> downloadReceipt(@PathVariable Long paymentId) {
        try {
            byte[] pdfBytes = receiptService.generateReceipt(paymentId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "receipt.pdf");

            Response<byte[]> response = Response.successfulResponse("Receipt generated successfully", pdfBytes);
            return ResponseEntity.ok().headers(headers).body(response);

        } catch (Exception e) {
            Response<byte[]> errorResponse = Response.failedResponse("Failed to generate receipt");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
