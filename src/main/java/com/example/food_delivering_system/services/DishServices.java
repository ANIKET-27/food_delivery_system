package com.example.food_delivering_system.services;

import com.example.food_delivering_system.DTO.DishDTO;


import java.util.List;

public interface DishServices {

    List<DishDTO> getAllDishes();

    DishDTO getDishById(Long id);

    DishDTO createDish(String name, String des, Double price);

    DishDTO updateDish(DishDTO dishDTO);

    void deleteDish(Long id);

}
