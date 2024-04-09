package com.example.food_delivering_system;

import com.example.food_delivering_system.DTO.*;
import com.example.food_delivering_system.entities.Dish;
import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.User;
import com.example.food_delivering_system.services.DishServices;
import com.example.food_delivering_system.services.impl.*;
import lombok.extern.java.Log;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@SpringBootTest

class FoodDeliveringSystemApplicationTests {


	@Autowired
	private  DishServicesImpl dishServices;

	@Autowired
	private UserServicesImpl userServices;

	@Autowired
	private OrderServicesImpl orderServices;

	@Autowired
	DriverServicesImpl driverServices;

	@Test
	void createDish(){

		dishServices.createDish("Momos","Tibet_Style",40.0);
		dishServices.createDish("Noodles","Soggy",80.0);

	}

	@Test
	void createDriver(){

		DriverDTO driverDTO = DriverDTO.builder()
				.username("Third-Driver")
				.available(true)
				.email("thirdD@hotmail.com")
				.orders(new ArrayList<>())
				.password("3password")
				.build();

        driverServices.createDriver(driverDTO);


	}


   @Test
   void createUser(){

	   UserDTO userDto = UserDTO.builder()
			   .userName("Alex")
			   .password("password")
			   .email("email.com")
			   .latitude(234.4)
			    .longitude(234.4)
			   .phoneNo("23434")
			   .cart(new HashMap<>())
			   .order(new ArrayList<>())
			   .build();

	   userServices.createUser(userDto);



   }

   @Test
   void testGetUserById(){
		UserDTO user = userServices.getUserById(1L);
	   System.out.println(user);
   }

   @Test
	void userCreatingOrder(){

	   PlaceOrderDTO placeOrderDTO = PlaceOrderDTO.builder()
			   .cart(new HashMap<>())
			   .deliveryInstructions("Can you make it Spicy?")
			   .user_id(1L)
			   .build();



	   Dish dish1 = Dish.builder()
			   .dish_id(3)
			   .build();

	   Dish dish2 = Dish.builder()
			   .dish_id(4)
			   .build();

	   placeOrderDTO.getCart().put(dish1,1.0);
	   placeOrderDTO.getCart().put(dish2,2.0);

	   userServices.createOrder(placeOrderDTO);



   }

   @Test
	void cancelOrder(){

		userServices.updateOrder(OrderDTO.builder().orderId(1L).build());


   }


   @Test
	void getUserOngoingOrder(){
		List<OrderDTO> ongoing = userServices.getAllOngoingOrders(1L);

		for(OrderDTO o : ongoing) System.out.println(o);

   }

   @Test
	void getUserCompletedOrder(){

		List<OrderDTO> completed = userServices.getAllCompletedOrders(1L);

		for(OrderDTO e : completed )  System.out.println(e);

   }

   @Test
	void testDeleteUser(){

		userServices.deleteUser(2L);

   }

   @Test
	void testAssignDriverToOrder(){

		driverServices.assignDriverToOrder(4L,2L);

   }

   @Test
	void testDriverUpdateStatus(){

      driverServices.updateStatusForDelivery(4L,"On-The-Way");

   }

   @Test
   void testOrderCompletedByDriver(){

		for(OrderDTO o : driverServices.getPastOrder(2L))
			System.out.println(o);

   }


	@Test
	void testOngoingByDriver(){

		for(OrderDTO o : driverServices.getCurrentOrder(2L))
			System.out.println(o);

	}


}
