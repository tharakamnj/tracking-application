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
    //latitude
    private float lat;
    //longitude
    private float lon;
    private String mobileNo;
    private Status status;


    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "serviceId")
    @JsonIgnore
    private SchoolService schoolService;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<UserTrip> trips = new ArrayList<>();

    public Client(String clientName, float lat, float lon, String mobileNo, Status status, SchoolService schoolService) {
        this.clientName = clientName;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.status = status;
        this.schoolService = schoolService;
    }

    public Client(Long clientId, String clientName, float lat, float lon, String mobileNo, Status status, SchoolService schoolService) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.status = status;
        this.schoolService = schoolService;
    }
}
