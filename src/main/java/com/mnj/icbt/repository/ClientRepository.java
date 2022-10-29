package com.mnj.icbt.repository;

import com.mnj.icbt.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByMobileNo(String mobileNo);

    Client findByUsername(String username);
}
