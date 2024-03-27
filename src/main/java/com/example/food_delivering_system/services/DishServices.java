package com.example.food_delivering_system.services;

import com.example.food_delivering_system.DTO.DishDTO;


import java.util.List;

public interface DishServices {

    List<DishDTO> getAllDishes();

    DishDTO getDishById(Long id);

    DishDTO createDish();

    DishDTO updateDish();

    void deleteDish(Long id);

}
