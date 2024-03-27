package com.example.food_delivering_system.services;

import com.example.food_delivering_system.DTO.OrderDTO;
import com.example.food_delivering_system.DTO.UserDTO;


import java.util.List;

public interface UserServices{

    List<UserDTO> getAllUsers();

    List<OrderDTO> getAllOrders(Long id);

    UserDTO getUserById(Long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    UserDTO placeOrder(UserDTO userDTO);

    OrderDTO cancelOrder(OrderDTO orderDTO);

    void deleteUser(Long id);

}