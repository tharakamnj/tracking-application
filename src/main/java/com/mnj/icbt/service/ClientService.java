package com.mnj.icbt.service;

import com.mnj.icbt.dto.ClientDTO;
import com.mnj.icbt.dto.DriverDTO;
import com.mnj.icbt.dto.UserTripDTO;
import com.mnj.icbt.utils.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {

    ResponseEntity<?> addClient(ClientDTO dto);

    ResponseEntity<?> getAllClients();

    ResponseEntity<?> updateClient(Long clientId, ClientDTO dto);

    ResponseEntity<?> getClientById(Long clientId);

    ResponseEntity<?> deleteClient(Long clientId);

    ResponseEntity<?> pickUpClient(UserTripDTO dto);

    ResponseEntity<?> dropOutClient(UserTripDTO dto);

}
