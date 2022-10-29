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

    private String firstName;

    private String lastName;

    private String username;

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

    public Client(String firstName, String lastName, String username, String password, float lat, float lon, String mobileNo, String deviceId, Status status, SchoolService schoolService) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.deviceId = deviceId;
        this.status = status;
        this.schoolService = schoolService;
    }

    public Client(Long clientId, String firstName, String lastName, String usernamee, String password, float lat, float lon, String mobileNo, String deviceId, Status status, SchoolService schoolService) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.deviceId = deviceId;
        this.status = status;
        this.schoolService = schoolService;
    }
}
