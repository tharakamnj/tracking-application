package com.mnj.icbt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;
    private String clientName;

    private String password;
    //latitude
    private float lat;
    //longitude
    private float lon;
    private String mobileNo;

    private String deviceId;
    private Status status;


    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "serviceId")
    @JsonIgnore
    private SchoolService schoolService;

    @OneToMany(mappedBy = "client", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<UserTrip> trips = new ArrayList<>();

    public Client(String clientName, String password, float lat, float lon, String mobileNo, String deviceId, Status status, SchoolService schoolService) {
        this.clientName = clientName;
        this.password = password;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.deviceId = deviceId;
        this.status = status;
        this.schoolService = schoolService;
    }

    public Client(Long clientId, String clientName, String password, float lat, float lon, String mobileNo, String deviceId, Status status, SchoolService schoolService) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.password = password;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.deviceId = deviceId;
        this.status = status;
        this.schoolService = schoolService;
    }
}
