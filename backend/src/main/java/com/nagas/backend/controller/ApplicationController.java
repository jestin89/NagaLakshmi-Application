package com.nagas.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagas.backend.entity.ApplicationAttachment;
import com.nagas.backend.model.ApplicationRequest;
import com.nagas.backend.model.ApplicationResponse;
import com.nagas.backend.model.AttachedResponse;
import com.nagas.backend.services.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
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
            save   = service.saveApplication(request);
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

   @GetMapping("/application/getByUserId/{userId}")
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

    @GetMapping("/application/getAllStudentDetails")
    public List<ApplicationRequest> getAllStudentDetails(){
        log.info("Entering the getAllStudentDetails methods");
        List<ApplicationRequest> response = null;
        try{
            response = service.getAllApplication();
        }catch(Exception e){
            log.info("Exception in getAllStudentDetails method:"+e.getMessage());
        }
        log.info("Leaving the getAllStudentDetails methods");
        return response;
    }

    @PostMapping("/application/uploadImage/{userId}")
    public String handleFileUpload(@PathVariable int userId , @RequestParam("file") MultipartFile file) {
        return service.store(file, userId);
    }

    @GetMapping("/application/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int fileId) {
        // Load file from database
        ApplicationAttachment dbFile = service.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getContent()));
    }
}
