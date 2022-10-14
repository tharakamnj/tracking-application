package com.mnj.icbt.service.impls;

import com.mnj.icbt.constant.ValidationMessages;
import com.mnj.icbt.dto.SchoolServiceDTO;
import com.mnj.icbt.entity.Driver;
import com.mnj.icbt.entity.SchoolService;
import com.mnj.icbt.repository.DriverRepository;
import com.mnj.icbt.repository.SchoolServiceRepository;
import com.mnj.icbt.service.SchoolServiceService;
import com.mnj.icbt.utils.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Slf4j
@Service
public class SchoolServiceServiceImpl implements SchoolServiceService {

    private SchoolServiceRepository serviceRepository;
    private DriverRepository driverRepository;

    public SchoolServiceServiceImpl(SchoolServiceRepository serviceRepository, DriverRepository driverRepository) {
        this.serviceRepository = serviceRepository;
        this.driverRepository = driverRepository;
    }


    @Override
    public ResponseEntity<?> saveSchoolService(SchoolServiceDTO dto) {
        log.debug("****************** add client is start. clientDTO: " + dto);
        CommonResponse commonResponse = new CommonResponse();
        /*if (isServiceByRootNo(dto.getRootNo())) {
            log.debug("This root number is already registered..");
            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.ALREADY_EXIST));
            commonResponse.setStatus(-1);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }*/

        Driver driver = driverRepository.findById(dto.getDriverId()).get();
        SchoolService service = serviceRepository.save(new SchoolService(
                dto.getRootNo(),
                dto.getDescription(),
                driver
        ));
        commonResponse.setStatus(1);
        commonResponse.setPayload(Collections.singletonList(service));
        log.debug("****************** add client is finished. response: " + service);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllSchoolService() {
        return null;
    }

    @Override
    public ResponseEntity<?> getSchoolServiceById(Long id) {

        CommonResponse commonResponse = new CommonResponse();
        if (!serviceRepository.existsById(id)) {

            commonResponse.setErrorMessages(Collections.singletonList(ValidationMessages.NOT_FOUND));
            commonResponse.setStatus(-1);
        }
        SchoolService service = serviceRepository.findById(id).get();
        commonResponse.setStatus(1);
        commonResponse.setPayload(Collections.singletonList(service));
        //log.debug("****************** add client is finished. response: " + service);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /*public boolean isServiceByRootNo(String rootNo) {
        Boolean isExist = false;
        SchoolService service = serviceRepository.schoolserviceFindByRootNo(rootNo);
        if (service != null) {
            isExist = true;
        }
        return isExist;
    }*/
}
