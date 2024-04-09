package com.example.food_delivering_system.repository;

import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {



}
