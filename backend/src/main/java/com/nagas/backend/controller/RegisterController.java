package com.nagas.backend.controller;

import com.nagas.backend.model.Register;
import com.nagas.backend.services.RegisterService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping({"/nagas/api"})
public class RegisterController {

    @Autowired
    private RegisterService service;

    public RegisterController() {
    }

    @PostMapping({"/register"})
    public String userRegister(@RequestBody Register register) {
        log.info("Entering the userRegister method:" + register.getUserName());
        Register result = null;

        try {
            result = this.service.saveUserRegister(register);
            System.err.println("REGISter id:" + result.getUserName());
        } catch (Exception e) {
            log.info("Exception in userRegister method:" + e.getMessage());
        }

        log.info("Leaving the userRegister method");
        return result.getUserName() + " saved successfully";
    }
}

