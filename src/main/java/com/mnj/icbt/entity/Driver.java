package com.mnj.icbt.entity;

import com.mnj.icbt.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Status status;

    //@OneToOne
    //private SchoolService schoolService

    @OneToMany
    private List<Trip> trips;

    public Driver(String name, String licenceNo, float lat, float lon, String mobileNo, Status status) {
        this.name = name;
        this.licenceNo = licenceNo;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.status = status;
    }

    public Driver(Long driverId, String name, String licenceNo, float lat, float lon, String mobileNo, Status status) {
        this.driverId = driverId;
        this.name = name;
        this.licenceNo = licenceNo;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.status = status;
    }
}
