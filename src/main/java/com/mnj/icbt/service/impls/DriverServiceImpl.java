package com.mnj.icbt.service.impls;

import com.mnj.icbt.constant.Status;
import com.mnj.icbt.constant.ValidationMessages;
import com.mnj.icbt.dto.DriverDTO;
import com.mnj.icbt.entity.Client;
import com.mnj.icbt.entity.Driver;
import com.mnj.icbt.entity.DriverTrip;
import com.mnj.icbt.entity.SchoolService;
import com.mnj.icbt.repository.DriverRepository;
import com.mnj.icbt.repository.SchoolServiceRepository;
import com.mnj.icbt.service.DriverService;
import com.mnj.icbt.utils.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DriverServiceImpl implements DriverService {

    private DriverRepository driverRepository;

    private SchoolServiceRepository serviceRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, SchoolServiceRepository serviceRepository) {
        this.driverRepository = driverRepository;
        this.serviceRepository = serviceRepository;
    }

    /**
     * This method use for create driver
     * @param dto
     * @return
     */
    @Override
    public ResponseEntity<CommonResponse> addDriver(DriverDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        if (isDriverByLicenceNo(dto.getLicenceNo())){
            log.debug("This licence number is already registered..");
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.ALREADY_EXIST));
            commonResponse.setStatus(-1);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        //SchoolService service = serviceRepository.findById(dto.getServiceId()).get();
        Driver driver = driverRepository.save(new Driver(
                dto.getUsername(),
                dto.getPassword(),
                dto.getLicenceNo(),
                dto.getLat(),
                dto.getLon(),
                dto.getMobileNo(),
                dto.getDeviceId(),
                dto.getStatus()
                //service
        ));
        commonResponse.setStatus(1);
        commonResponse.setPayload(Collections.singletonList(driver));
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse> getAllDrivers() {
        CommonResponse commonResponse = new CommonResponse();
            List<Driver> driverList = driverRepository.findAll();
            if (driverList.isEmpty()){
                commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
                commonResponse.setStatus(-1);
            }else {
                commonResponse.setPayload(Collections.singletonList(driverList));
                commonResponse.setStatus(1);
            }
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateDriver(Long driverId, DriverDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        if (!driverRepository.existsById(driverId)){
            log.debug("Not found Driver, Check your inputs.");
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
            commonResponse.setStatus(-1);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        //SchoolService service = serviceRepository.findById(dto.getServiceId()).get();
        Driver driver = driverRepository.save(new Driver(
                driverId,
                dto.getUsername(),
                dto.getPassword(),
                dto.getLicenceNo(),
                dto.getLat(),
                dto.getLon(),
                dto.getMobileNo(),
                dto.getDeviceId(),
                dto.getStatus()
                //service
        ));
        commonResponse.setStatus(1);
        commonResponse.setPayload(Collections.singletonList(driver));
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getDriverById(Long driverId) {
        CommonResponse commonResponse = new CommonResponse();
        if (!driverRepository.existsById(driverId)){
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
            commonResponse.setStatus(-1);
        }else {
            Optional<Driver> driver = driverRepository.findById(driverId);
            commonResponse.setPayload(Collections.singletonList(driver));
            commonResponse.setStatus(1);
        }
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteDriver(Long driverId) {
        CommonResponse commonResponse = new CommonResponse();
        if (!driverRepository.existsById(driverId)){
            log.debug("Not found Driver, Check your inputs.");
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
            commonResponse.setStatus(-1);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        Driver driver = driverRepository.findById(driverId).get();
        driver.setStatus(Status.DELETE);
        Driver driver1 = driverRepository.save(driver);
        commonResponse.setStatus(1);
        commonResponse.setPayload(Collections.singletonList(driver1));
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTripsByDriverId(Long driverId) {
        CommonResponse commonResponse = new CommonResponse();
        if (!driverRepository.existsById(driverId)) {
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
            commonResponse.setStatus(-1);
        } else {
            Driver driver = driverRepository.findById(driverId).get();

            List<DriverTrip> trips = driver.getDriverTrips();

            commonResponse.setPayload(Collections.singletonList(trips));
            commonResponse.setStatus(1);
        }
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getClientByDriverId(Long driverId) {
        CommonResponse commonResponse = new CommonResponse();
        if (!driverRepository.existsById(driverId)) {
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
            commonResponse.setStatus(-1);
        } else {
            Driver driver = driverRepository.findById(driverId).get();
            SchoolService service = serviceRepository.findByDriver(driver);
            List<Client> clients = service.getClients();
            commonResponse.setPayload(Collections.singletonList(clients));
            commonResponse.setStatus(1);
        }
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateLocation(DriverDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        if (!driverRepository.existsById(dto.getDriverId())){
            log.debug("Not found Driver, Check your inputs.");
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
            commonResponse.setStatus(-1);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        Driver driver = driverRepository.findById(dto.getDriverId()).get();
        driver.setLon(dto.getLon());
        driver.setLat(dto.getLat());
        Driver driver1 = driverRepository.save(driver);
        commonResponse.setStatus(1);
        commonResponse.setPayload(Collections.singletonList(driver1));
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> shareLocation(Long driverId) {
        CommonResponse commonResponse = new CommonResponse();
        HashMap<String,Float> location = new HashMap<>();
        if (!driverRepository.existsById(driverId)){
            log.debug("Not found Driver, Check your inputs.");
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
            commonResponse.setStatus(-1);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
        Driver driver = driverRepository.findById(driverId).get();
        location.put("lat",driver.getLat());
        location.put("long",driver.getLon());
        commonResponse.setStatus(1);
        commonResponse.setPayload(Collections.singletonList(location));
        return new ResponseEntity<>(commonResponse,HttpStatus.OK);
    }


    /**
     * This method use for check existing drivers using licence Number
     * @param licenceNo
     * @return
     */
    public boolean isDriverByLicenceNo(String licenceNo){
        Boolean isExist = false;
        Driver user = driverRepository.findByLicenceNo(licenceNo);
        if (user != null){
            isExist = true;
        }
        return isExist;
    }
}
