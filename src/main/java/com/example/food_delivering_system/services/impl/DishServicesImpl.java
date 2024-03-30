package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.DTO.DishDTO;
import com.example.food_delivering_system.entities.Dish;
import com.example.food_delivering_system.repository.DishRepository;
import com.example.food_delivering_system.services.DishServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DishServicesImpl implements DishServices {

    private final DishRepository dishRepository;

    @Autowired
    public DishServicesImpl(DishRepository dishRepository){
        this.dishRepository = dishRepository;
    }
    @Override
    public List<DishDTO> getAllDishes() {
        List<DishDTO> dishDTOList = new ArrayList<>();

        List<Dish>  dishList = dishRepository.findAll();

        for(Dish d : dishList) dishDTOList.add(Convetor.dishToDishDto(d));

        return dishDTOList;

    }

    @Override
    public DishDTO getDishById(Long id) {
        Optional<Dish>  optDish = dishRepository.findById(id);

        if(optDish.isEmpty()) throw  new RuntimeException("Dish Not Available");

        return Convetor.dishToDishDto(optDish.get());

    }

    @Override
    public DishDTO createDish(DishDTO dishDTO) {
       Dish d = Convetor.dishDtoToDish(dishDTO);
       Dish saved = dishRepository.save(d);

        return DishDTO.builder()
               .id(saved.getId())
               .build();

    }

    @Override
    public DishDTO updateDish(DishDTO dishDTO) {
        Dish d = Convetor.dishDtoToDish(dishDTO);
        d = dishRepository.save(d);

        return Convetor.dishToDishDto(d);
    }

    @Override
    public void deleteDish(Long id) {

        Optional<Dish>  optDish = dishRepository.findById(id);

        if(optDish.isEmpty()) throw  new RuntimeException("Dish Not Available To Delete");

        dishRepository.deleteById(id);

    }
}
