package com.mnj.icbt.dto;

import com.mnj.icbt.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {

    private String driverId;
    private String name;
    private String licenceNo;
    //latitude
    private float lat;
    //longitude
    private float lon;
    private String mobileNo;
    private Status status;
}
