package com.mnj.icbt.controller;

import com.mnj.icbt.dto.ClientDTO;
import com.mnj.icbt.dto.DriverDTO;
import com.mnj.icbt.dto.UserTripDTO;
import com.mnj.icbt.service.ClientService;
import com.mnj.icbt.utils.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client")
    public ResponseEntity<?> addClient(@RequestBody ClientDTO clientDTO){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = clientService.addClient(clientDTO);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @PutMapping("/client/{clientId}")
    public ResponseEntity<?> updateClient(@PathVariable("clientId") Long clientId, @RequestBody ClientDTO clientDTO){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = clientService.updateClient(clientId,clientDTO);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }


    @GetMapping("/client")
    public ResponseEntity<?> getAllClients(){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = clientService.getAllClients();
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getClientById(@PathVariable("clientId") Long clientId){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = clientService.getClientById(clientId);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @DeleteMapping("/client/{clientId}")
    public ResponseEntity<?> deleteClientById(@PathVariable("clientId") Long clientId){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = clientService.deleteClient(clientId);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @PostMapping("/pickup")
    public ResponseEntity<?> pickupClient(@RequestBody UserTripDTO dto){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = clientService.pickUpClient(dto);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @PostMapping("/dropOut")
    public ResponseEntity<?> dropOutClient(@RequestBody UserTripDTO dto){
        ResponseEntity responseEntity = null;
        CommonResponse commonResponse = null;
        try {
            responseEntity = clientService.dropOutClient(dto);
        }catch (Exception e){
            log.error(e.getMessage());
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }
}
