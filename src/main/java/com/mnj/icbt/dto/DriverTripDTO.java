package com.mnj.icbt.dto;

import com.mnj.icbt.constant.TripType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverTripDTO {

    private Long driverTripId;

    private TripType tripType;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    //for start location
    private float startLat;
    private float startLon;

    //for end location
    private float endLat;
    private float endLon;

    //private Long driverId;
}
