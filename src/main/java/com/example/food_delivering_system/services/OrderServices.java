package com.example.food_delivering_system.services;

import com.example.food_delivering_system.DTO.OrderDTO;
import com.example.food_delivering_system.DTO.UserDTO;

import java.util.List;

public interface OrderServices {

  List<OrderDTO> getAllOrders();

  List<OrderDTO> getOngoingOrders();

  List<OrderDTO> getCompletedOrders();

  OrderDTO getOrderBYId(Long id);


  void updateOrderStatus(Long orderId,String status);



}
