package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.DTO.DriverDTO;
import com.example.food_delivering_system.DTO.OrderDTO;
import com.example.food_delivering_system.DTO.UserDTO;
import com.example.food_delivering_system.entities.Driver;
import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.User;


public class Convetor {

   static User userDtoToUser(UserDTO userDTO){
        return new User();

    }

    static  UserDTO userToUserDto(User user){
        return new UserDTO();
    }

    static Order orderDtoToOrder(OrderDTO orderDTO){
       return new Order();
    }

    static OrderDTO orderToOrderDto(Order order){
       return new OrderDTO();
    }

    static Driver driverDtotoDriver(DriverDTO driverDTO){
       return new Driver();
    }

    static DriverDTO driverToDriverDto(Driver driver){
       return new DriverDTO();
    }


}
