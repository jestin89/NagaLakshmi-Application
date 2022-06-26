package com.nagas.backend.model;

import lombok.Data;

@Data
public class ApplicationRequest {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private int mobileNo;
    private String emailId;
    private String educationType;
    private String collegeName;
    private String collegeCode;
    private String collegeDetails;
    private String schoolName;
    private String schoolCode;
    private String schoolDetails;
    private String created;
    private String update;
    private int userId;
}