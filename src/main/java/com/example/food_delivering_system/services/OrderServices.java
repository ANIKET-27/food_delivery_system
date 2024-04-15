package com.example.food_delivering_system.services;

import com.example.food_delivering_system.dto.Response.OrderDTO;

import java.util.List;

public interface OrderServices {


  OrderDTO getOrderBYId(Long id);

  void updateOrderStatus(Long orderId, Integer status);


}
