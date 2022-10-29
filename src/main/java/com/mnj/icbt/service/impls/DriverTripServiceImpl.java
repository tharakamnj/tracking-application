package com.mnj.icbt.service.impls;

import com.mnj.icbt.constant.ValidationMessages;
import com.mnj.icbt.dto.DriverTripDTO;
import com.mnj.icbt.entity.Client;
import com.mnj.icbt.entity.Driver;
import com.mnj.icbt.entity.DriverTrip;
import com.mnj.icbt.entity.SchoolService;
import com.mnj.icbt.repository.DriverRepository;
import com.mnj.icbt.repository.DriverTripRepository;
import com.mnj.icbt.service.DriverTripService;
import com.mnj.icbt.utils.CommonResponse;
import com.mnj.icbt.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class DriverTripServiceImpl implements DriverTripService {

    private DriverTripRepository repository;

    private DriverRepository driverRepository;


    @Autowired
    public DriverTripServiceImpl(DriverTripRepository repository, DriverRepository driverRepository) {
        this.repository = repository;
        this.driverRepository = driverRepository;
    }

    @Override
    public ResponseEntity<?> startTrip(Long driverId, DriverTripDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        Map<String,Long> map = new HashMap<>();
        try {
            Driver driver = driverRepository.findById(driverId).get();
            //driver.setSchoolService(null);
            Long tripId = repository.save(new DriverTrip(
                    dto.getTripType(),
                    DateUtil.getFormattedDateTime(DateUtil.getCurrentTime()),
                    null,
                    dto.getStartLat(),
                    dto.getStartLon(),
                    0,
                    0,
                    driver
            )).getDriverTripId();
            map.put("driverTripId",tripId);
            commonResponse.setStatus(1);
            commonResponse.setPayload(Collections.singletonList(map));
        }catch (Exception ex){
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            commonResponse.setStatus(-1);
            return new ResponseEntity<>(commonResponse,HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> endTrip(DriverTripDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        Map<String,Long> map = new HashMap<>();
        try {
            DriverTrip trip = repository.findById(dto.getDriverTripId()).get();
            trip.setEndLat(dto.getEndLat());
            trip.setEndLon(dto.getEndLon());
            trip.setEndTime(DateUtil.getFormattedDateTime(DateUtil.getCurrentTime()));
            Long tripId = repository.save(trip).getDriverTripId();
            map.put("driverTripId",tripId);
            commonResponse.setStatus(1);
            commonResponse.setPayload(Collections.singletonList(map));
        }catch (Exception ex){
            commonResponse.setErrorMessages(Collections.singletonList(ex.getMessage()));
            commonResponse.setStatus(-1);
            return new ResponseEntity<>(commonResponse,HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }
}
