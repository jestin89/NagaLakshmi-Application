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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mobileno")
    private int mobileNo;

    @Column(name = "mailid")
    private String emailId;

    @Column(name = "education_type")
    private String educationType;

    @Column(name = "college_name")
    private String collegeName;

    @Column(name = "college_code")
    private String collegeCode;

    @Column(name = "college_details")
    private String collegeDetails;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_code")
    private String schoolCode;

    @Column(name = "school_details")
    private String schoolDetails;

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
