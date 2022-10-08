package com.mnj.icbt.service.impls;

import com.mnj.icbt.constant.Status;
import com.mnj.icbt.constant.ValidationMessages;
import com.mnj.icbt.dto.ClientDTO;
import com.mnj.icbt.entity.Client;
import com.mnj.icbt.repository.ClientRepository;
import com.mnj.icbt.service.ClientService;
import com.mnj.icbt.utils.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public ResponseEntity<?> addClient(ClientDTO dto) {
        log.debug("****************** add client is start. clientDTO: " +dto);
        CommonResponse commonResponse = new CommonResponse();
        if (isClientByMobileNo(dto.getMobileNo())){
            log.debug("This mobile number is already registered..");
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.ALREADY_EXIST));
            commonResponse.setStatus(-1);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        Client client = clientRepository.save(new Client(
                dto.getClientName(),
                dto.getLat(),
                dto.getLon(),
                dto.getMobileNo(),
                dto.getStatus()
        ));
        commonResponse.setStatus(1);
        commonResponse.setPayload(Collections.singletonList(client));
        log.debug("****************** add client is finished. response: " +client);
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllClients() {
        CommonResponse commonResponse = new CommonResponse();
        List<Client> clients = clientRepository.findAll();
        if (clients.isEmpty()){
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
            commonResponse.setStatus(-1);
        }else {
            commonResponse.setPayload(Collections.singletonList(clients));
            commonResponse.setStatus(1);
        }
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateClient(Long clientId, ClientDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        if (!clientRepository.existsById(clientId)){
            log.debug("Not found Client, Check your inputs.");
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
            commonResponse.setStatus(-1);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        Client client = clientRepository.save(new Client(
                clientId,
                dto.getClientName(),
                dto.getLat(),
                dto.getLon(),
                dto.getMobileNo(),
                dto.getStatus()
        ));
        commonResponse.setStatus(1);
        commonResponse.setPayload(Collections.singletonList(client));
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getClientById(Long clientId) {
        CommonResponse commonResponse = new CommonResponse();
        if (!clientRepository.existsById(clientId)){
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
            commonResponse.setStatus(-1);
        }else {
            Client client = clientRepository.findById(clientId).get();
            commonResponse.setPayload(Collections.singletonList(client));
            commonResponse.setStatus(1);
        }
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteClient(Long clientId) {
        CommonResponse commonResponse = new CommonResponse();
        if (!clientRepository.existsById(clientId)){
            log.debug("Not found Client, Check your inputs.");
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
            commonResponse.setStatus(-1);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        Client client = clientRepository.findById(clientId).get();
        client.setStatus(Status.DELETE);
        Client client1 = clientRepository.save(client);
        commonResponse.setStatus(1);
        commonResponse.setPayload(Collections.singletonList(client1));
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    public boolean isClientByMobileNo(String mobile) {
        Boolean isExist = false;
        Client client = clientRepository.findByMobileNo(mobile);
        if (client != null) {
            isExist = true;
        }
        return isExist;
    }
}
