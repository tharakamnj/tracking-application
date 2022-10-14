package com.mnj.icbt.repository;

import com.mnj.icbt.entity.SchoolService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolServiceRepository extends JpaRepository<SchoolService,Long> {
}
