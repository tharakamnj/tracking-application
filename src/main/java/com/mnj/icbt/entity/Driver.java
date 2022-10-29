package com.mnj.icbt.entity;

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

    private String firstName;

    private String lastName;
    private String username;

    private String password;
    private String licenceNo;
    //latitude
    private float lat;
    //longitude
    private float lon;
    private String mobileNo;

    private String deviceId;
    private Status status;

    /*@OneToOne(mappedBy = "driver")
    private SchoolService schoolService;*/

    @OneToMany(mappedBy = "driver", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<DriverTrip> driverTrips = new ArrayList<>();

    public Driver(Long driverId,String firstName, String lastName, String username,String password, String licenceNo, float lat, float lon, String mobileNo, String deviceId, Status status) {
        this.driverId = driverId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.licenceNo = licenceNo;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.deviceId = deviceId;
        this.status = status;
    }

    public Driver(String firstName, String lastName, String username,String password, String licenceNo, float lat, float lon, String mobileNo, String deviceId, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.licenceNo = licenceNo;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.deviceId = deviceId;
        this.status = status;
    }
}
