package com.nagas.backend.model;

import lombok.Data;

@Data
public class Register {
    private int id;
    private String userName;
    private String password;
    private String confirmPassword;
    private String emailId;
    private int mobileNo;
    private String role;
}
