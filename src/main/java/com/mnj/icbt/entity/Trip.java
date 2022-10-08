package com.mnj.icbt.entity;

import com.mnj.icbt.constant.TripType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trip {

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

    /*@ManyToOne
    private List<Driver> drivers;
    @ManyToMany
    private List<Client> clients;*/
}
