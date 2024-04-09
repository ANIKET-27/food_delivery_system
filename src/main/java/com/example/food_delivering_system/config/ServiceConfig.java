package com.example.food_delivering_system.config;

import com.example.food_delivering_system.repository.DishRepository;
import com.example.food_delivering_system.repository.DriverRepository;
import com.example.food_delivering_system.repository.OrderRepository;
import com.example.food_delivering_system.repository.UserRepository;
import com.example.food_delivering_system.services.UserServices;
import com.example.food_delivering_system.services.impl.DishServicesImpl;
import com.example.food_delivering_system.services.impl.DriverServicesImpl;
import com.example.food_delivering_system.services.impl.OrderServicesImpl;
import com.example.food_delivering_system.services.impl.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {


    @Autowired
    @Bean
    public DriverServicesImpl driverServicesImpl(DriverRepository driverRepository, OrderRepository orderRepository){
        return new DriverServicesImpl(driverRepository, orderRepository);
    }

    @Autowired
    @Bean
    public UserServicesImpl userServicesImpl(UserRepository userRepository, OrderRepository orderRepository){
        return new UserServicesImpl(userRepository, orderRepository);
    }

    @Autowired
    @Bean
    public DishServicesImpl dishServicesImpl(DishRepository dishRepository){
        return new DishServicesImpl(dishRepository);
    }

    @Autowired
    @Bean
    public OrderServicesImpl orderServicesImpl(OrderRepository orderRepository){
        return new OrderServicesImpl(orderRepository);
    }

}
