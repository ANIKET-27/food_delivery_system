package com.example.food_delivering_system;

import com.example.food_delivering_system.DTO.UserDTO;
import com.example.food_delivering_system.services.impl.UserServicesImpl;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class FoodDeliveringSystemApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(FoodDeliveringSystemApplication.class, args);



	}

}
