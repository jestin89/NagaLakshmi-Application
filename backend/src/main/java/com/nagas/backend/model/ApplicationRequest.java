package com.nagas.backend.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ApplicationRequest {
    private int id;
    private String studentName;
    private String registerNo;
    private String education;
    private String instituteName;
    private String course;
    private String department;
    private int mobileNo;
    private String emailId;
    private int userId;
   // private MultipartFile bonafide;
}