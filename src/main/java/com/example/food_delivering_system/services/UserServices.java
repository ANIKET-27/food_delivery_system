package com.example.food_delivering_system.services;

import com.example.food_delivering_system.DTO.OrderDTO;
import com.example.food_delivering_system.DTO.UserDTO;


import java.util.List;

public interface UserServices{

    List<UserDTO> getAllUsers();

    List<OrderDTO> getAllCompletedOrders(Long id);
    List<OrderDTO> getAllOngoingOrders(Long id);

    UserDTO getUserById(Long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    UserDTO createOrder(UserDTO userDTO, String instruction);

    OrderDTO updateOrder(OrderDTO  orderDTO);

    void deleteUser(Long id);


}