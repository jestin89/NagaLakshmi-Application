package com.nagas.backend.services;

import com.nagas.backend.entity.EmailTemplate;
import com.nagas.backend.entity.UserRegister;
import com.nagas.backend.model.LoginRequest;
import com.nagas.backend.model.Register;
import com.nagas.backend.repository.EmailTemplateRepository;
import com.nagas.backend.repository.RegisterRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private EmailTemplateRepository emailTemplateRepository;
    @Autowired
    private EmailService emailService;

    public RegisterService() {
    }

    public Register saveUserRegister(Register register) {
        String templateName = "application_request";
        new Register();
        Map<String, Object> valueMap = new HashMap();
        UserRegister user = this.convertToUserRegister(register);
        Register result = this.convertToRegister((UserRegister)this.registerRepository.save(user));
        EmailTemplate emailTemplate = this.emailTemplateRepository.findByTemplateName(templateName);
        if (register != null && !StringUtils.isEmpty(result.getRole()) && !result.getRole().equalsIgnoreCase("admin")) {
            if (result.getRole().equalsIgnoreCase("subscribers")) {
                StringJoiner join = new StringJoiner(",");
                if (emailTemplate != null) {
                    join.add(emailTemplate.getTo());
                }

                if (!StringUtils.isEmpty(result.getEmailId())) {
                    join.add(result.getEmailId());
                }

                System.err.println("Subscriber:" + join.toString());
            } else if (result.getRole().equalsIgnoreCase("student")) {
                try {
                    if (!result.getUserName().isEmpty()) {
                        valueMap.put("requestApplication", result);
                        this.emailService.sendMail(emailTemplate, valueMap);
                    }
                } catch (Exception var8) {
                    log.info("Exception in saveUserRegister:", var8);
                }
            }
        }

        return result;
    }

    private Register convertToRegister(UserRegister user) {
        Register register = new Register();
        register.setId(user.getId());
        register.setUserName(user.getUserName());
        register.setPassword(user.getPassword());
        register.setConfirmPassword(user.getConfirmPassword());
        register.setEmailId(user.getEmailId());
        register.setMobileNo(user.getMobileNo());
        register.setRole(user.getRole());
        return register;
    }

    private UserRegister convertToUserRegister(Register register) {
        UserRegister user = new UserRegister();
        user.setUserName(register.getUserName());
        user.setPassword(register.getPassword());
        user.setConfirmPassword(register.getConfirmPassword());
        user.setEmailId(register.getEmailId());
        user.setMobileNo(register.getMobileNo());
        user.setRole(register.getRole());
        return user;
    }

    public String validate(LoginRequest request) {
        log.info("Entering the validate method");
        StringJoiner joiner = new StringJoiner(",");

        try {
            if (!StringUtils.isEmpty(request.getUserName())) {
                UserRegister userValidate = this.registerRepository.findByUserName(request.getUserName());
                if (userValidate != null) {
                    if (!StringUtils.isEmpty(userValidate.getUserName())) {
                        if (request.getUserName().equalsIgnoreCase(userValidate.getUserName())) {
                            if (!StringUtils.isEmpty(userValidate.getPassword())) {
                                if (request.getPassword().equalsIgnoreCase(userValidate.getPassword())) {
                                    if (!StringUtils.isEmpty(userValidate.getRole())) {
                                        joiner.add(userValidate.getRole());
                                    }
                                } else {
                                    joiner.add("Invalid Password");
                                }
                            }
                        } else {
                            joiner.add("Invalid UserName");
                        }
                    }
                } else {
                    joiner.add("Invalid UserName");
                }
            }
        } catch (Exception var4) {
            log.info("Exception in Validate:" + var4);
        }

        log.info("Leaving the validate method");
        return joiner.toString();
    }
}
