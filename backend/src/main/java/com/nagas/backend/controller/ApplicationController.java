package com.nagas.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagas.backend.entity.ApplicationAttachment;
import com.nagas.backend.model.ApplicationRequest;
import com.nagas.backend.model.ApplicationResponse;
import com.nagas.backend.model.AttachedResponse;
import com.nagas.backend.services.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping({"/nagas/api"})
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    @PostMapping(value= "/application/save")
    public String saveApplication(@RequestBody ApplicationRequest request){
        System.err.println("REquest:"+request.getStudentName());

        log.info("Entering the saveApplication method:"+request.getStudentName());
        String save = null;
        try{
//            ObjectMapper objectMapper = new ObjectMapper();
           // request = objectMapper.readValue(request,ApplicationRequest.class);
            List<ApplicationAttachment> fileList = new ArrayList<ApplicationAttachment>();
            MultipartFile file = request.getBonafide();
                ApplicationAttachment attachment = new ApplicationAttachment();
                String fileContentType = file.getContentType();
                String sourceFileContent = new String(file.getBytes());
                String fileName = file.getOriginalFilename();
               // FileModal fileModal = new FileModal(fileName, sourceFileContent, fileContentType);
                attachment.setFileName(fileName);
                attachment.setContent(file.getBytes());
                attachment.setFileType(fileContentType);
                // Adding file into fileList
                fileList.add(attachment);

            save   = service.saveApplication(request,fileList);
        }catch(Exception e){
            log.info("Exception in saveApplication:"+e);
        }
        log.info("Leaving the saveApplication method:"+request.getStudentName());
        return save;
    }
    @PostMapping("/application/update")
    public String updateApplication(@RequestBody ApplicationRequest request){
        log.info("Entering the updateApplication method:"+request.getStudentName());
        String update = null;
        try{
            update   = service.updateApplication(request);
        }catch(Exception e){
            log.info("Exception in updateApplication:"+e);
        }
        log.info("Leaving the updateApplication method:"+request.getStudentName());
        return update;

    }

   @GetMapping("/application/getbyuserid/{userId}")
    public ApplicationResponse getApplicationDetails(@PathVariable("userId") Integer id){
        log.info("Entering the getApplicationDetails method:"+id);
       ApplicationResponse response = null;
        try{
            response   = service.getApplication(id);
        }catch(Exception e){
            log.info("Exception in getApplicationDetails method:"+e.getMessage());
        }

       log.info("Leaving the getApplicationDetails method");
       return response;
    }

}
