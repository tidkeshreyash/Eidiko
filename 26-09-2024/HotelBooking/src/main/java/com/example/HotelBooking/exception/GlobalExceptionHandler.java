package com.example.HotelBooking.exception;

import com.example.HotelBooking.commonResponse.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response<?>> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Response<?> response = Response.failedResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<?>> globalExceptionHandler(Exception ex, WebRequest request) {
        Response<?> response = Response.failedResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RoomOccupiedException.class)
    public ResponseEntity<Response<?>> handleRoomOccupiedException(RoomOccupiedException ex, WebRequest request) {
        Response<?> response = Response.failedResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
