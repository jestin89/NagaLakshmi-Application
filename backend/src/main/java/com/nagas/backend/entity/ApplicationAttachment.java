package com.nagas.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "application_attachment")
public class ApplicationAttachment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;

    @Column(name = "name")
    private String fileName;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @Column(name = "filetype")
    private String fileType;

    @ManyToOne
    private Application application;

}
