package com.mnj.icbt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Float startLat;
    private Float startLon;

    //for end location
    private float endLat;
    private float endLon;

    @ManyToOne
    @JoinColumn(name = "driverId", referencedColumnName = "driverId")
    @JsonIgnore
    private Driver driver;

    public DriverTrip(TripType tripType, LocalDateTime startTime, LocalDateTime endTime, float startLat, float startLon,
                      float endLat, float endLon, Driver driver) {
        this.tripType = tripType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startLat = startLat;
        this.startLon = startLon;
        this.endLat = endLat;
        this.endLon = endLon;
        this.driver = driver;
    }
}
