package com.example.food_delivering_system.repository;

import com.example.food_delivering_system.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Long> {
}
