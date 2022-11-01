package com.mnj.icbt.controller;

import com.mnj.icbt.dto.DriverDTO;
import com.mnj.icbt.service.DriverService;
import com.mnj.icbt.utils.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class DriverController {

    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/driverReg")
    public ResponseEntity<?> addDriver(@RequestBody DriverDTO driverDTO){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = driverService.addDriver(driverDTO);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @PutMapping("/driver/{driverId}")
    public ResponseEntity<?> updateDriver(@PathVariable("driverId") Long driverId, @RequestBody DriverDTO driverDTO){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = driverService.updateDriver(driverId,driverDTO);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }


    @GetMapping("/drivers")
    public ResponseEntity<?> getAllDrivers(){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = driverService.getAllDrivers();
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<?> getDriverById(@PathVariable("driverId") Long driverId){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = driverService.getDriverById(driverId);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @DeleteMapping("/driver/{driverId}")
    public ResponseEntity<?> deleteDriverById(@PathVariable("driverId") Long driverId){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = driverService.deleteDriver(driverId);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }
    @GetMapping("/driverTrip/{driverId}")
    public ResponseEntity<?> getTripsByDriverId(@PathVariable("driverId") Long driverId){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = driverService.getTripsByDriverId(driverId);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @GetMapping("/driverClient/{driverId}")
    public ResponseEntity<?> getClientByDriverId(@PathVariable("driverId") Long driverId){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = driverService.getClientByDriverId(driverId);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @PutMapping("/location")
    public ResponseEntity<?> updateLocation(@RequestBody DriverDTO dto){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = driverService.updateLocation(dto);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @GetMapping("/location/{clientId}")
    public ResponseEntity<?> shareLocation(@PathVariable("clientId") Long clientId){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = driverService.shareLocation(clientId);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }
}
