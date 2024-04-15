package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.repository.OrderRepository;
import com.example.food_delivering_system.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServicesImpl implements OrderServices {

    public final OrderRepository orderRepository;

    @Autowired
    public OrderServicesImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }


    @Override
    public OrderDTO getOrderBYId(Long id) {

        Optional<Order> optionalOrder = orderRepository.findById(id);

        if(optionalOrder.isEmpty()) throw new RuntimeException("ORDER NOT FOUND BY ID");

        return Convetor.orderToOrderDto(optionalOrder.get());

    }

    @Override
    public void updateOrderStatus(Long orderId ,Integer status) {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if(optionalOrder.isEmpty()) throw new RuntimeException("ORDER NOT FOUND BY ID");

        Order order = optionalOrder.get();

        if(order.getOrderStatus() == -1 || order.getOrderStatus() == 3)
            throw new RuntimeException("Order is either completed or cancelled");

        order.setOrderStatus(status);

        Order orderSaved = orderRepository.save(optionalOrder.get());


         Convetor.orderToOrderDto(orderSaved);


    }


}
