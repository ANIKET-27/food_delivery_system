package com.example.food_delivering_system.repository;

import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT d FROM User d WHERE d.available = true" )
    List<User> findAllAvailableDriver();

    @Query("SELECT d FROM User d WHERE d.available = false" )
    List<User> findAllUnavailableDriver();

    @Query("SELECT u FROM User u WHERE u.role.id = 2")
    List<User> findAllCustomers();

    @Query("SELECT u FROM User u WHERE  u.role.id = 3")
    List<User> findAllDrivers();

    @Query("SELECT u FROM User u Where u.userName = :userName")
    User findByUserName(String userName);

}
