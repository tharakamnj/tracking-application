package com.mnj.icbt.service;

import com.mnj.icbt.dto.SchoolServiceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SchoolServiceService {
    ResponseEntity<?> saveSchoolService(SchoolServiceDTO dto);

    ResponseEntity<?> getAllSchoolService();

    ResponseEntity<?> getSchoolServiceById(Long id);
}
