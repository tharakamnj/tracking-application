package com.mnj.icbt.repository;

import com.mnj.icbt.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    Driver findByLicenceNo(String licenceNo);
}
