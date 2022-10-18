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
public class UserTripDTO {

    private Long tripId;

    private TripType tripType;

    private LocalDateTime pickUp;
    private LocalDateTime dropOut;

    //for pick location
    private float pickLat;
    private float pickLon;

    //for drop location
    private float dropLat;
    private float dropLon;

    private String driverTripId;
    private Long clientId;
}
