package com.nagas.backend.services;

import com.nagas.backend.entity.Application;
import com.nagas.backend.entity.ApplicationAttachment;
import com.nagas.backend.entity.EmailTemplate;
import com.nagas.backend.entity.UserRegister;
import com.nagas.backend.model.*;
import com.nagas.backend.repository.ApplicationAttachmentRepository;
import com.nagas.backend.repository.ApplicationRepository;
import com.nagas.backend.repository.EmailTemplateRepository;
import com.nagas.backend.repository.RegisterRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private ApplicationAttachmentRepository applicationAttachmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;
    @Autowired
    private EmailService emailService;


    public String saveApplication(ApplicationRequest request, List<ApplicationAttachment> fileList) {
        log.info("Entering the ApplicationService save method");
        String save = null;
        try {
            Optional<UserRegister> user = registerRepository.findById(request.getUserId());
            if (user.isPresent()) {

                Application application = convertToEntity(request, user.get());
                Application response = applicationRepository.save(application);
                if (response != null) {

                    for (ApplicationAttachment fileModal : fileList) {
                        fileModal.setApplication(response);
                        applicationAttachmentRepository.save(fileModal);
                    }
                }
                save = "Application Saved Successfully.";
                sendMailToSubscribers(response);
            }

        } catch (Exception e) {
            log.info("Excpetion in ApplicationService save method:" + e.getMessage());
        }
        log.info("Leaving the ApplicationService save method");
        return save;
    }

    private void sendMailToSubscribers(Application mail) {
        String role = mail.getUser().getRole();
        String templateName = "application_request";
        Map<String, Object> valueMap = new HashMap();
        Response result = new Response();
        result.setApplicationId(mail.getUser().getId());
        result.setFirstName(mail.getFirstName());
        result.setLastName(mail.getLastName());
        result.setEducationType(mail.getEducationType());
        result.setEmailId(mail.getEmailId());
        result.setMobileNo(mail.getMobileNo());
        EmailTemplate emailTemplate = emailTemplateRepository.findByTemplateName(templateName);
        if (result != null && !StringUtils.isEmpty(role) && !role.equalsIgnoreCase("admin")) {
            if (role.equalsIgnoreCase("student")) {
                try {

                    valueMap.put("requestApplication", result);
                    this.emailService.sendMail(emailTemplate, valueMap);

                } catch (Exception e) {
                    log.info("Exception in saveUserRegister:", e);
                }
            }
        }
    }

//    private ApplicationRequest convertToDTOApplicationRequest(Application save) {
//        ApplicationRequest response = modelMapper.map(save, ApplicationRequest.class);
//        return response;
//    }


    public String updateApplication(ApplicationRequest request) {
        log.info("Entering the ApplicationService update method");
        String update = null;
        try {

        } catch (Exception e) {
            log.info("Excpetion in ApplicationService update method:" + e.getMessage());
        }
        log.info("Leaving the ApplicationService update method");
        return update;
    }


    private ApplicationRequest convertToDTO(Optional<Application> save) {
        ApplicationRequest response = modelMapper.map(save.get(), ApplicationRequest.class);
        return response;
    }

    private Application convertToEntity(ApplicationRequest request, UserRegister user) {
        Application application = new Application();
        application.setFirstName(request.getFirstName());
        application.setLastName(request.getLastName());
        application.setGender(request.getGender());
        application.setMobileNo(request.getMobileNo());
        application.setEmailId(request.getEmailId());
        application.setEducationType(request.getEducationType());
        application.setCollegeName(request.getCollegeName());
        application.setCollegeCode(request.getCollegeCode());
        application.setCollegeDetails(request.getCollegeDetails());
        application.setSchoolName(request.getSchoolName());
        application.setSchoolCode(request.getSchoolCode());
        application.setSchoolDetails(request.getSchoolDetails());
        application.setUser(user);
        return application;
    }

    public ApplicationResponse getApplication(Integer id) {
        ApplicationResponse response = new ApplicationResponse();
        ApplicationRequest application = convertToDTO(applicationRepository.findByUserId(id));
        //List<AttachedResponse> attached = applicationAttachmentRepository.findByApplicationId(application.getId());
        response.setRequest(application);
        return response;
    }


}
