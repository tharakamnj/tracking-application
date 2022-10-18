package com.mnj.icbt.dto;

import com.mnj.icbt.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {

    private Long driverId;
    private String username;
    private String password;
    private String licenceNo;
    private Long serviceId;
    //latitude
    private float lat;
    //longitude
    private float lon;
    private String mobileNo;
    private String deviceId;
    private Status status;
}
