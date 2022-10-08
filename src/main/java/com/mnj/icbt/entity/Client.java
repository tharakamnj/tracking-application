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

    @ManyToMany
    private List<Trip> trips;

    public Client(String clientName, float lat, float lon, String mobileNo, Status status) {
        this.clientName = clientName;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.status = status;
    }

    public Client(Long clientId, String clientName, float lat, float lon, String mobileNo, Status status) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.lat = lat;
        this.lon = lon;
        this.mobileNo = mobileNo;
        this.status = status;
    }
}
