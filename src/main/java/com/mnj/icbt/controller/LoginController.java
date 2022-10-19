package com.mnj.icbt.controller;

import com.mnj.icbt.dto.LoginDTO;
import com.mnj.icbt.entity.Client;
import com.mnj.icbt.entity.Driver;
import com.mnj.icbt.repository.ClientRepository;
import com.mnj.icbt.repository.DriverRepository;
import com.mnj.icbt.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("api/v1")
public class LoginController {

    private DriverRepository driverRepository;

    private ClientRepository clientRepository;

    @Autowired
    public LoginController(DriverRepository driverRepository, ClientRepository clientRepository) {
        this.driverRepository = driverRepository;
        this.clientRepository = clientRepository;
    }

    @PostMapping("/driver/login")
    public ResponseEntity<?> driverLog(@RequestBody LoginDTO dto){
        CommonResponse commonResponse = new CommonResponse();
        try {
            Driver driver = driverRepository.findByMobileNo(dto.getMobileNo());
            if (driver.getMobileNo().equals(dto.getMobileNo()) && driver.getPassword().equals(dto.getPassword())){
                driver.setDriverTrips(null);
                driver.setPassword(null);
                commonResponse.setPayload(Collections.singletonList(driver));
                commonResponse.setStatus(1);
            }else {
                commonResponse.setStatus(1);
                commonResponse.setErrorMessages(Collections.singletonList("invalid user credentials"));
            }
        }catch (Exception ex){
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(commonResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/client/login")
    public ResponseEntity<?> clientLog(@RequestBody LoginDTO dto){
        CommonResponse commonResponse = new CommonResponse();
        try {
            Client client = clientRepository.findByMobileNo(dto.getMobileNo());
            if (client.getMobileNo() == dto.getMobileNo() && client.getPassword() == dto.getPassword()){
                client.setTrips(null);
                client.setSchoolService(null);
                client.setPassword(null);
                commonResponse.setPayload(Collections.singletonList(client));
                commonResponse.setStatus(1);
            }else {
            commonResponse.setStatus(1);
            commonResponse.setErrorMessages(Collections.singletonList("invalid user credentials"));
        }
        }catch (Exception ex){
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(commonResponse, HttpStatus.ACCEPTED);
    }
}
