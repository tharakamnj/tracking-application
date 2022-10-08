package com.mnj.icbt.service.impls;

import com.mnj.icbt.constant.Status;
import com.mnj.icbt.constant.ValidationMessages;
import com.mnj.icbt.dto.DriverDTO;
import com.mnj.icbt.entity.Driver;
import com.mnj.icbt.repository.DriverRepository;
import com.mnj.icbt.service.DriverService;
import com.mnj.icbt.utils.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DriverServiceImpl implements DriverService {

    private DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
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
        Driver driver = driverRepository.save(new Driver(
                dto.getName(),
                dto.getLicenceNo(),
                dto.getLat(),
                dto.getLon(),
                dto.getMobileNo(),
                dto.getStatus()
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
        Driver driver = driverRepository.save(new Driver(
                driverId,
                dto.getName(),
                dto.getLicenceNo(),
                dto.getLat(),
                dto.getLon(),
                dto.getMobileNo(),
                dto.getStatus()
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
            Driver driver = driverRepository.findById(driverId).get();
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
        commonResponse.setPayload(Collections.singletonList(driver));
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
