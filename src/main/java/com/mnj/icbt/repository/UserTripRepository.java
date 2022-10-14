package com.mnj.icbt.repository;

import com.mnj.icbt.entity.UserTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTripRepository extends JpaRepository<UserTrip,Long> {
}
