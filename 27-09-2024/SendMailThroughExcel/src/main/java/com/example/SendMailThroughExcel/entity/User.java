package com.example.SendMailThroughExcel.entity;


import lombok.Data;

import java.util.List;


@Data
public class User {
    private int userId;
    private String name;
    private String email;
    private String phone;
    private String city;
    private List<String> fileNames;



}

