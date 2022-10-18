package com.mnj.icbt.controller;

import com.mnj.icbt.dto.DriverTripDTO;
import com.mnj.icbt.service.DriverTripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/dtrip")
public class DriverTripController {

    private DriverTripService driverTripService;

    public DriverTripController(DriverTripService driverTripService) {
        this.driverTripService = driverTripService;
    }

    @PostMapping("/start/{driverId}")
    public ResponseEntity<?> start(@PathVariable("driverId") Long driverId, @RequestBody DriverTripDTO dto){
        return driverTripService.startTrip(driverId,dto);
    }

    @PutMapping("/end/{driverId}")
    public ResponseEntity<?> end(@PathVariable("driverId") Long driverId, @RequestBody DriverTripDTO dto){
        return driverTripService.endTrip(driverId,dto);
    }
}
