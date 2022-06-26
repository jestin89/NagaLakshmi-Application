package com.nagas.backend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
public class AttachedResponse {
    private int id;
    private String fileName;
    private byte[] content;
    private String fileType;
}
