package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.DTO.OrderDTO;
import com.example.food_delivering_system.DTO.UserDTO;
import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.User;
import com.example.food_delivering_system.repository.OrderRepository;
import com.example.food_delivering_system.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
        return Convetor.orderToOrderDto(orderRepository.findById(id).get());
    }

    @Override
    public UserDTO createOrder(UserDTO userDTO) {

        // Create order using the userDTO
        Order order = new Order();



        return userDTO;
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {

        Order order = Convetor.orderDtoToOrder(orderDTO);

        return orderDTO;
    }

//    public Order setDriverForOrder(Long orderId, Long driverId) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
//
//        Driver driver = driverRepository.findById(driverId)
//                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + driverId));
//
//        order.setDriver(driver);
//        return orderRepository.save(order);
//    }


}
