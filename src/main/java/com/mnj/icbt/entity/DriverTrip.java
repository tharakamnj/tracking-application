package com.mnj.icbt.entity;

import com.mnj.icbt.constant.TripType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DriverTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long driverTripId;

    @Enumerated(EnumType.STRING)
    private TripType tripType;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    //for start location
    private float startLat;
    private float startLon;

    //for end location
    private float endLat;
    private float endLon;

    @ManyToOne
    @JoinColumn(name = "driverId", referencedColumnName = "driverId")
    private Driver driver;



}
