package com.mnj.icbt.service;

import com.mnj.icbt.dto.DriverTripDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DriverTripService {
    ResponseEntity<?> startTrip(Long driverId, DriverTripDTO dto);

    ResponseEntity<?> endTrip(Long driverId, DriverTripDTO dto);

}
