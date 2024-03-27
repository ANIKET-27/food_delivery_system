package com.example.food_delivering_system.repository;

import com.example.food_delivering_system.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {

}
