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
        name = "email_template"
)
public class EmailTemplate {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    @Column(
            name = "name"
    )
    private String templateName;
    @Column(
            name = "body"
    )
    private String body;
    @Column(
            name = "subject"
    )
    private String subject;
    @Column(
            name = "toRecipients"
    )
    private String to;
    @Column(
            name = "ccRecipients"
    )
    private String cc;
    @Column(
            name = "bccRecipients"
    )
    private String bcc;
}
