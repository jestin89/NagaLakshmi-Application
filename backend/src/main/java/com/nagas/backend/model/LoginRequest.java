package com.nagas.backend.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;

}
