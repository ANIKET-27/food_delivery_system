package com.example.food_delivering_system.services;

import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.dto.Request.PlaceOrderDTO;
import com.example.food_delivering_system.dto.Response.UserDTO;


import java.util.List;


public interface UserServices{

    List<OrderDTO> getAllCompletedOrders(Long id);

    List<OrderDTO> getAllOngoingOrders(Long id);

    UserDTO getUserById(Long id);

    UserDTO createUser(CreateUserDTO dto);

    UserDTO updateUser(Long userId , CreateUserDTO userDTO);

    OrderDTO createOrder(PlaceOrderDTO placeOrderDTO);

    OrderDTO cancelOrder(Long orderId);

    UserDTO getByUserName(String name);

    void deleteUser(Long id);


}