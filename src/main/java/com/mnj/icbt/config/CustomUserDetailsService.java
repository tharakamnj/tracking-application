package com.mnj.icbt.config;

import com.mnj.icbt.entity.Client;
import com.mnj.icbt.entity.Driver;
import com.mnj.icbt.repository.ClientRepository;
import com.mnj.icbt.repository.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private DriverRepository driverRepository;

    private ClientRepository clientRepository;

    @Autowired
    public CustomUserDetailsService(DriverRepository driverRepository, ClientRepository clientRepository) {
        this.driverRepository = driverRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Driver driver = new Driver();
        try {
            driver = driverRepository.getByUsername(username);
            if (driver == null){
                Client client = clientRepository.findByUsername(username);
                return new org.springframework.security.core.userdetails.User(client.getUsername(),client.getPassword(),new ArrayList<>());
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
        return new org.springframework.security.core.userdetails.User(driver.getUsername(),driver.getPassword(),new ArrayList<>());
    }

    public Object getUserByUsername(String username){
        Driver driver = new Driver();
        Client client = new Client();
        try {
            driver = driverRepository.getByUsername(username);
            if (driver == null){
                client = clientRepository.findByUsername(username);
                client.setPassword(null);
                client.setTrips(null);
                return client;
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return ex;
        }
        if (driver == null && client == null) {
            return "Not found user.";
        }
        driver.setPassword(null);
        driver.setDriverTrips(null);
        return driver;
    }
}
