package com.mnj.icbt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mnj.icbt.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long driverId;
    private String name;
    private String licenceNo;
    //latitude
    private float lat;
    //longitude
    private float lon;
    private String mobileNo;

    private String deviceId;
    private Status status;

    @OneToOne(mappedBy = "driver")
    private SchoolService schoolService;

    @OneToMany(mappedBy = "driver", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<DriverTrip> driverTrips = new ArrayList<>();

    public Driver(Long driverId, String name, String licenceNo, float lat, float lon, String mobileNo, String deviceId, Status status) {
        this.driverId = driverId;
        this.name = name;
        this.licenceNo = licenceNo;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.deviceId = deviceId;
        this.status = status;
    }

    public Driver(String name, String licenceNo, float lat, float lon, String mobileNo, String deviceId, Status status) {
        this.name = name;
        this.licenceNo = licenceNo;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.deviceId = deviceId;
        this.status = status;
    }
}
