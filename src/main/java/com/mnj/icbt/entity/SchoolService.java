package com.mnj.icbt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToOne
    private Driver driver;

    @OneToMany
    private List<Client> clients;
}
