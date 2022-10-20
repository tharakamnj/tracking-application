package com.mnj.icbt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnj.icbt.constant.TripType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tripId;

    @Enumerated(EnumType.STRING)
    private TripType tripType;

    private LocalDateTime pickUp;
    private LocalDateTime dropOut;

    //for pick location
    private float pickLat;
    private float pickLon;

    //for drop location
    private float dropLat;
    private float dropLon;

    private Long driverTripId;

    @ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "clientId")
    @JsonIgnore
    private Client client;

    public UserTrip(TripType tripType, LocalDateTime pickUp, LocalDateTime dropOut, float pickLat, float pickLon,
                    float dropLat, float dropLon, Long driverTripId, Client client) {
        this.tripType = tripType;
        this.pickUp = pickUp;
        this.dropOut = dropOut;
        this.pickLat = pickLat;
        this.pickLon = pickLon;
        this.dropLat = dropLat;
        this.dropLon = dropLon;
        this.driverTripId = driverTripId;
        this.client = client;
    }
}
