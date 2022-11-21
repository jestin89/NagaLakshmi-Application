package com.nagas.backend.model;

import lombok.Data;

import java.util.List;
@Data
public class AdminResponse {
    private List<Register> student;
    private List<Register> subscriber;
}
