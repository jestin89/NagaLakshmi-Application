package com.nagas.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(
        name = "user_register"
)
public class UserRegister {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    @Column(
            name = "username"
    )
    private String userName;
    @Column(
            name = "password"
    )
    private String password;
    @Column(
            name = "confirmpassword"
    )
    private String confirmPassword;
    @Column(
            name = "emailid"
    )
    private String emailId;
    @Column(
            name = "mobileno"
    )
    private int mobileNo;
    @Column(
            name = "role"
    )
    private String role;

}
