package com.nagas.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.List;

@JsonInclude(Include.NON_NULL)
@Data
public class EmailRequest {
    private String template;
    private String subject;
    private String body;
    private String from;
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;
    private String fileName;
    private String content;
    private String contentType;

}
