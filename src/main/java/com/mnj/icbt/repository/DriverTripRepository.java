package com.mnj.icbt.repository;

import com.mnj.icbt.entity.DriverTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverTripRepository extends JpaRepository<DriverTrip,Long> {
}
