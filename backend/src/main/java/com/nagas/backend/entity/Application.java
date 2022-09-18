package com.nagas.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "student_application")
public class Application {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    private int id;

    @Column(name = "studentName")
    private String studentName;

    @Column(name = "registerNo")
    private String registerNo;

    @Column(name = "education")
    private String education;

    @Column(name = "mobileno")
    private int mobileNo;

    @Column(name = "mailid")
    private String emailId;

    @Column(name = "instituteName")
    private String instituteName;

    @Column(name = "course")
    private String course;

    @Column(name = "department")
    private String department;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date update;

    @OneToOne
    private UserRegister user;


//    @OneToMany(cascade = CascadeType.ALL)
////    @JoinColumn(name="fk_attached_id")
//    private List<ApplicationAttachment> attachment;
}
