package com.mnj.icbt.dto;

import com.mnj.icbt.entity.Client;
import com.mnj.icbt.entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolServiceDTO {

    private Long serviceId;
    private String rootNo;
    private String description;
    private Long driverId;
}
