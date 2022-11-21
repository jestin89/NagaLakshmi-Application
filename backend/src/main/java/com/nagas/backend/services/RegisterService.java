package com.nagas.backend.services;

import com.nagas.backend.entity.EmailTemplate;
import com.nagas.backend.entity.UserRegister;
import com.nagas.backend.model.AdminResponse;
import com.nagas.backend.model.LoginRequest;
import com.nagas.backend.model.LoginResponse;
import com.nagas.backend.model.Register;
import com.nagas.backend.repository.EmailTemplateRepository;
import com.nagas.backend.repository.RegisterRepository;

import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private EmailService emailService;


    public Register saveUserRegister(Register register) {
        String templateName = "application_request";
        Register result = new Register();
        Map<String, Object> valueMap = new HashMap();
        UserRegister user = this.convertToUserRegister(register);
        result = this.convertToRegister((UserRegister) this.registerRepository.save(user));

        if (result != null && result.getRole().equalsIgnoreCase("subscribers")) {
            EmailTemplate emailTemplate = this.emailTemplateRepository.findByTemplateName(templateName);
            StringJoiner join = new StringJoiner(",");
            if (emailTemplate != null) {
                join.add(emailTemplate.getTo());
            }

            if (!StringUtils.isEmpty(result.getEmailId())) {
                join.add(result.getEmailId());
            }

            System.err.println("Subscriber:" + join.toString());
            emailTemplate.setTo(join.toString());
            emailTemplateRepository.save(emailTemplate);
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

    public LoginResponse validate(LoginRequest request) {
        log.info("Entering the validate method");
        LoginResponse response = new LoginResponse();
        StringJoiner joiner = new StringJoiner(",");

        try {
            if (!StringUtils.isEmpty(request.getUserName())) {
                UserRegister userValidate = this.registerRepository.findByEmailId(request.getUserName());
                if (userValidate != null) {
                    if (!StringUtils.isEmpty(userValidate.getEmailId())) {
                        if (request.getUserName().equalsIgnoreCase(userValidate.getEmailId())) {
                            if (!StringUtils.isEmpty(userValidate.getPassword())) {
                                if (request.getPassword().equalsIgnoreCase(userValidate.getPassword())) {
                                    if (!StringUtils.isEmpty(userValidate.getRole())) {
                                        joiner.add(userValidate.getRole());
                                        response.setId(userValidate.getId());
                                        response.setEmailId(userValidate.getEmailId());
                                        response.setMobileNo(userValidate.getMobileNo());
                                        response.setRole(userValidate.getRole());
                                        response.setStatus("success");
                                    }
                                } else {
                                    joiner.add("Invalid Password");
                                    response.setStatus("error");
                                }
                            }
                        } else {
                            joiner.add("Invalid UserName");
                            response.setStatus("error");
                        }
                    }
                } else {
                    joiner.add("Invalid UserName");
                    response.setStatus("error");
                }
                response.setMessage(joiner.toString());
            }
        } catch (Exception var4) {
            log.info("Exception in Validate:" + var4);
        }

        log.info("Leaving the validate method");
        return response;
    }

    public AdminResponse getAll() {
        AdminResponse response = new AdminResponse();
        log.info("Entering the getAll service method");
        try {
            List<UserRegister> register = registerRepository.findAll();
            response = convertToAdmin(register);
        } catch (Exception e) {
            log.info("Exception in getAll service method");
        }
        log.info("Leaving the getAll service method");
        return response;
    }

    private AdminResponse convertToAdmin(List<UserRegister> register) {
        AdminResponse response = new AdminResponse();
        List<Register> student = new ArrayList<>();
        List<Register> subscriber = new ArrayList<>();
        try {
            if (!CollectionUtils.isEmpty(register)) {
                register.stream().filter(s -> s.getRole().equalsIgnoreCase("Student")).forEach(stu -> {
                    Register studentRegister = new Register();
                    studentRegister.setId(stu.getId());
                    studentRegister.setUserName(stu.getUserName());
                    studentRegister.setEmailId(stu.getEmailId());
                    studentRegister.setMobileNo(stu.getMobileNo());
                    studentRegister.setRole(stu.getRole());
                    student.add(studentRegister);
                });
                response.setStudent(student);
                register.stream().filter(s -> s.getRole().equalsIgnoreCase("Subscriber")).forEach(sub -> {
                    Register subRegister = new Register();
                    subRegister.setId(sub.getId());
                    subRegister.setUserName(sub.getUserName());
                    subRegister.setEmailId(sub.getEmailId());
                    subRegister.setMobileNo(sub.getMobileNo());
                    subRegister.setRole(sub.getRole());
                    subscriber.add(subRegister);
                });
                response.setSubscriber(subscriber);
            }
        }catch(Exception e){
            log.info("Exception in convert to admin method:"+e.getMessage());
        }
        return response;
    }
}
