package com.example.food_delivering_system.repository;

import com.example.food_delivering_system.entities.Driver;
import com.example.food_delivering_system.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver,Long> {

    @Query("SELECT d FROM Driver d WHERE d.available = true" )
    List<Driver> findAllAvailableDriver();


}
