package com.example.HotelBooking.Controller;


import com.example.HotelBooking.Entity.User;
import com.example.HotelBooking.Service.UserService;
import com.example.HotelBooking.commonResponse.Response;
import jakarta.servlet.http.HttpServletRequest;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public ResponseEntity<Response<User>> createUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("panNumber") String panNumber,
            @RequestParam("password") String password,
            @RequestParam("aadhaarCardPhoto") MultipartFile aadhaarCardPhoto) {

        try {
            User user = userService.createUser(name, email, phoneNumber, address, city, panNumber, password, aadhaarCardPhoto);
            return ResponseEntity.ok(Response.successfulResponse("User created successfully", user));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Response.failedResponse("Failed to create user"));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response<User>> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(Response.successfulResponse("User updated successfully", updatedUser));
    }


    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Response<String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(Response.successfulResponse("Customer deleted successfully"));
    }



    @GetMapping("/userDetails/{id}")
    public ResponseEntity<Response<User>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(Response.successfulResponse("User details fetched successfully", user));
    }


    @GetMapping("/allUsers")
    public ResponseEntity<Response<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(Response.successfulResponse("All users fetched successfully", users));
    }


    @GetMapping("/userAadhaarData/{id}")
    public ResponseEntity<Response<String>> getUserAadhaarData(@PathVariable Long id) {
        try {
            String aadhaarData = userService.getAadhaarCardData(id);
            return ResponseEntity.ok(Response.successfulResponse("Aadhaar data fetched successfully", aadhaarData));
        } catch (IOException | TesseractException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Response.failedResponse("Failed to extract Aadhaar data"));
        }
    }

}
