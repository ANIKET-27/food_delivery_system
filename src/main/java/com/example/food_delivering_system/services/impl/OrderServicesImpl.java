package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.DTO.OrderDTO;
import com.example.food_delivering_system.DTO.UserDTO;
import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.User;
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
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> ordersDto = new ArrayList<>();

        List<Order> allOrders = orderRepository.findAll();

        for(Order order : allOrders) ordersDto.add(Convetor.orderToOrderDto(order));

        return ordersDto;

    }

    @Override
    public List<OrderDTO> getOngoingOrders() {
        List<OrderDTO> ordersDto = new ArrayList<>();

        List<Order> allOrders = orderRepository.findOngoingOrders();

        for(Order order : allOrders) ordersDto.add(Convetor.orderToOrderDto(order));

        return ordersDto;
    }

    @Override
    public List<OrderDTO> getCompletedOrders() {

        List<OrderDTO> ordersDto = new ArrayList<>();

        List<Order> allOrders = orderRepository.findCompletedOrders();

        for(Order order : allOrders) ordersDto.add(Convetor.orderToOrderDto(order));

        return ordersDto;
    }

    @Override
    public OrderDTO getOrderBYId(Long id) {

        Optional<Order> optionalOrder = orderRepository.findById(id);

        if(optionalOrder.isEmpty()) throw new RuntimeException("ORDER NOT FOUND BY ID");

        return Convetor.orderToOrderDto(optionalOrder.get());

    }

    @Override
    public void updateOrderStatus(Long orderId ,String status) {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if(optionalOrder.isEmpty()) throw new RuntimeException("ORDER NOT FOUND BY ID");

        optionalOrder.get().setOrderStatus(status);

        Order orderSaved = orderRepository.save(optionalOrder.get());

         Convetor.orderToOrderDto(orderSaved);


    }


}
