package com.example.food_delivering_system.config;

import com.example.food_delivering_system.repository.DishRepository;
import com.example.food_delivering_system.repository.OrderRepository;
import com.example.food_delivering_system.repository.RoleRepository;
import com.example.food_delivering_system.repository.UserRepository;
import com.example.food_delivering_system.services.impl.AdminServicesImpl;
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
    public DriverServicesImpl driverServicesImpl(UserRepository driverRepository, OrderRepository orderRepository){
        return new DriverServicesImpl(driverRepository, orderRepository);
    }

    @Autowired
    @Bean
    public UserServicesImpl userServicesImpl(UserRepository userRepository, OrderRepository orderRepository, DishRepository dishRepository){
        return new UserServicesImpl(userRepository, orderRepository, dishRepository);
    }

    @Autowired
    @Bean
    public AdminServicesImpl adminServicesImpl(DishRepository dishRepository, UserRepository userRepository,
                                               OrderRepository orderRepository, RoleRepository roleRepository){
        return new AdminServicesImpl(dishRepository, userRepository, orderRepository, roleRepository);
    }

    @Autowired
    @Bean
    public OrderServicesImpl orderServicesImpl(OrderRepository orderRepository){
        return new OrderServicesImpl(orderRepository);
    }

}
