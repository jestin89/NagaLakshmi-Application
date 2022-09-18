package com.nagas.backend.controller;

import com.nagas.backend.model.LoginRequest;
import com.nagas.backend.model.LoginResponse;
import com.nagas.backend.model.Register;
import com.nagas.backend.services.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping({"/nagas/api"})
public class LoginController {

    @Autowired
    private RegisterService registerService;

    public LoginController() {
    }

    @PostMapping({"/login"})
    public LoginResponse loginValidation(@RequestBody LoginRequest request) {
        log.info("Entering the login validation method:" + request.getUserName());
        LoginResponse loginDetails = null;

        try {
            loginDetails = registerService.validate(request);
        } catch (Exception var4) {
            log.info("Exception in login validation:" + var4);
        }

        log.info("Leaving the login validation method");
        return loginDetails;
    }
}

