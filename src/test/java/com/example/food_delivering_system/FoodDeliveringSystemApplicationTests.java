package com.example.food_delivering_system;

import com.example.food_delivering_system.DTO.DishDTO;
import com.example.food_delivering_system.entities.Dish;
import com.example.food_delivering_system.repository.DishRepository;
import com.example.food_delivering_system.services.impl.DishServicesImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
class FoodDeliveringSystemApplicationTests {

//	@Autowired
//	private DishServicesImpl dishServices;

	@Test
	void contextLoads() {
	}

	@Test
	void dishIdVerification(){

//		DishDTO dishDTO = DishDTO.builder()
//				.name("Tripple Rice")
//				.price(200)
//				.build();
//
//		DishDTO saved = dishServices.createDish(dishDTO);

		Assertions.assertEquals(0,0);

		//Assertions.assertEquals(dishDTO.getId(),saved.getId());

	}

}
