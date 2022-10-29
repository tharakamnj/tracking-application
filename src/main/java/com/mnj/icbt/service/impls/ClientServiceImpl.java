package com.mnj.icbt.service.impls;

import com.mnj.icbt.constant.Status;
import com.mnj.icbt.constant.TripType;
import com.mnj.icbt.constant.ValidationMessages;
import com.mnj.icbt.dto.ClientDTO;
import com.mnj.icbt.dto.UserTripDTO;
import com.mnj.icbt.entity.Client;
import com.mnj.icbt.entity.SchoolService;
import com.mnj.icbt.entity.UserTrip;
import com.mnj.icbt.repository.ClientRepository;
import com.mnj.icbt.repository.SchoolServiceRepository;
import com.mnj.icbt.repository.UserTripRepository;
import com.mnj.icbt.service.ClientService;
import com.mnj.icbt.utils.CommonResponse;
import com.mnj.icbt.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    private SchoolServiceRepository serviceRepository;

    private UserTripRepository tripRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, SchoolServiceRepository serviceRepository, UserTripRepository tripRepository) {
        this.clientRepository = clientRepository;
        this.serviceRepository = serviceRepository;
        this.tripRepository = tripRepository;
    }


    @Override
    public ResponseEntity<?> addClient(ClientDTO dto) {
        log.debug("****************** add client is start. clientDTO: " +dto);
        CommonResponse commonResponse = new CommonResponse();
        if (isClientByMobileNo(dto.getMobileNo())){
            log.debug("This mobile number is already registered..");
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.ALREADY_EXIST));
            commonResponse.setStatus(-1);
            return new ResponseEntity<>(commonResponse, HttpStatus.CONFLICT);
        }
        SchoolService service = serviceRepository.findById(dto.getServiceId()).get();
        if(service == null){
            log.debug("Service ID: "+dto.getServiceId()+" not found.");
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
            commonResponse.setStatus(-1);
            return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
        }
        /*service.setDriver(null);
        service.setClients(null);*/
        Client client = clientRepository.save(new Client(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getUsername(),
                bcryptEncoder.encode(dto.getPassword()),
                dto.getLat(),
                dto.getLon(),
                dto.getMobileNo(),
                dto.getDeviceId(),
                dto.getStatus(),
                service
        ));
        client.setSchoolService(null);
        client.setPassword(null);
        commonResponse.setStatus(1);
        commonResponse.setPayload(Collections.singletonList(client));
        log.debug("****************** add client is finished. response: " +client);
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllClients() {
        CommonResponse commonResponse = new CommonResponse();
        List<Client> clients = clientRepository.findAll();
        clients.stream().forEach(client -> client.setPassword(null));
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
        SchoolService service = serviceRepository.findById(dto.getServiceId()).get();
        Client client = clientRepository.save(new Client(
                clientId,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getUsername(),
                bcryptEncoder.encode(dto.getPassword()),
                dto.getLat(),
                dto.getLon(),
                dto.getMobileNo(),
                dto.getDeviceId(),
                dto.getStatus(),
                service
        ));
        client.setPassword(null);
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
            client.setPassword(null);
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
        client1.setPassword(null);
        commonResponse.setStatus(1);
        commonResponse.setPayload(Collections.singletonList(client1));
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> pickUpClient(UserTripDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            Client client = clientRepository.findById(dto.getClientId()).get();
            UserTrip trip = tripRepository.save(new UserTrip(
                    dto.getTripType(),
                    DateUtil.getFormattedDateTime(DateUtil.getCurrentTime()),
                    null,
                    dto.getPickLat(),
                    dto.getPickLon(),
                    0,
                    0,
                    dto.getDriverTripId(),
                    client
            ));
            commonResponse.setStatus(1);
            commonResponse.setPayload(Collections.singletonList(trip.getTripId()));
        }catch (Exception ex){
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            return new ResponseEntity<>(commonResponse,HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> dropOutClient(UserTripDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        try {

            List<UserTrip> userTrips = tripRepository.findAll();
            List<UserTrip> userTripsNew = new ArrayList<>();
            List<UserTrip> userTripsNew1 = new ArrayList<>();
            for (UserTrip trip:userTrips) {
                if (trip.getDropOut() == null){
                    userTripsNew1.add(trip);
                }
            }
            for (UserTrip trip:userTripsNew1) {
                if (trip.getClient().getClientId().equals(dto.getClientId())){
                    userTripsNew.add(trip);
                }
            }
            UserTrip userTrip = userTripsNew.get(0);
            userTrip.setDropOut(DateUtil.getFormattedDateTime(DateUtil.getCurrentTime()));
            userTrip.setDropLat(dto.getDropLat());
            userTrip.setDropLon(dto.getDropLon());
            UserTrip trip = tripRepository.save(userTrip);
            commonResponse.setStatus(1);
            commonResponse.setPayload(Collections.singletonList(trip.getTripId()));
        }catch (Exception ex){
            commonResponse.setStatus(-1);
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            return new ResponseEntity<>(commonResponse,HttpStatus.EXPECTATION_FAILED);
        }
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
