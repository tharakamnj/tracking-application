package com.mnj.icbt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SchoolService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serviceId;
    private String rootNo;

    private String description;

    @OneToOne
    @JoinColumn(name = "driver_id")
    @JsonIgnore
    private Driver driver;

    @OneToMany(mappedBy = "schoolService", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Client> clients = new ArrayList<>();

    public SchoolService(String rootNo, String description, Driver driver) {
        this.rootNo = rootNo;
        this.description = description;
        this.driver = driver;
    }
}
