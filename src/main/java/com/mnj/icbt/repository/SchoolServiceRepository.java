package com.mnj.icbt.repository;

import com.mnj.icbt.entity.Driver;
import com.mnj.icbt.entity.SchoolService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SchoolServiceRepository extends JpaRepository<SchoolService,Long> {
    //@Query(value = "select * from schoolservice where"+d)
    //@Transactional
    SchoolService findByDriver(Driver driver);
}
