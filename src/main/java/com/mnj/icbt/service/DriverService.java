package com.mnj.icbt.service;

import com.mnj.icbt.dto.DriverDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DriverService {
    ResponseEntity<?> addDriver(DriverDTO dto);

    ResponseEntity<?> getAllDrivers();

    ResponseEntity<?> updateDriver(Long driverId, DriverDTO dto);

    ResponseEntity<?> getDriverById(Long driverId);

    ResponseEntity<?> deleteDriver(Long driverId);
}
