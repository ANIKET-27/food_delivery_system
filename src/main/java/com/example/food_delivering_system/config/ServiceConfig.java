package com.example.food_delivering_system.config;

import com.example.food_delivering_system.repository.*;
import com.example.food_delivering_system.services.impl.AdminServicesImpl;
import com.example.food_delivering_system.services.impl.DriverServicesImpl;
import com.example.food_delivering_system.services.impl.OrderServicesImpl;
import com.example.food_delivering_system.services.impl.UserServicesImpl;
import com.example.food_delivering_system.services.impl.security.JwtService;
import com.example.food_delivering_system.services.impl.security.RefreshTokenService;
import com.example.food_delivering_system.services.impl.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


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

    @Autowired
    @Bean
    public UserDetailServiceImpl userDetailServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return new UserDetailServiceImpl(userRepository,passwordEncoder);
    }

    @Autowired
    @Bean
    public RefreshTokenService refreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository){
        return new RefreshTokenService(userRepository,refreshTokenRepository);
    }


}
