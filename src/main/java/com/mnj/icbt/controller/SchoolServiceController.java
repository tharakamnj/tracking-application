package com.mnj.icbt.controller;

import com.mnj.icbt.dto.SchoolServiceDTO;
import com.mnj.icbt.service.SchoolServiceService;
import com.mnj.icbt.utils.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class SchoolServiceController {

    private SchoolServiceService service;

    public SchoolServiceController(SchoolServiceService service) {
        this.service = service;
    }

    @PostMapping("/service")
    public ResponseEntity<?> saveService(@RequestBody SchoolServiceDTO serviceDTO){
        ResponseEntity responseEntity;
        CommonResponse commonResponse = null;
        try {
            responseEntity = service.saveSchoolService(serviceDTO);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }
}
