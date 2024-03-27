package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.DTO.OrderDTO;
import com.example.food_delivering_system.DTO.UserDTO;
import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.User;
import com.example.food_delivering_system.repository.OrderRepository;
import com.example.food_delivering_system.repository.UserRepository;
import com.example.food_delivering_system.services.OrderServices;
import com.example.food_delivering_system.services.UserServices;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;
    private final OrderServicesImpl orderServicesImpl;

    @Autowired
    public UserServicesImpl(UserRepository userRepository, OrderServicesImpl orderServicesImpl){
        this.userRepository = userRepository;
        this.orderServicesImpl = orderServicesImpl;

    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public List<OrderDTO> getAllOrders(Long id) {
        List<OrderDTO> list = new ArrayList<>();
        List<Order> orders = userRepository.findOrdersByUserId(id);

        for(Order order : orders) list.add(Convetor.orderToOrderDto(order));

        return list;
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        return Convetor.userToUserDto(optUser.get());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = Convetor.userDtoToUser(userDTO);
        userRepository.save(user);
        return userDTO;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user = Convetor.userDtoToUser(userDTO);

        return userDTO;
    }

    @Override
    public UserDTO placeOrder(UserDTO userDTO) {

        updateUser(userDTO);
        return  orderServicesImpl.createOrder(userDTO);

    }

    @Override
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        return orderServicesImpl.updateOrder(orderDTO);
    }

    @Override
    public void deleteUser(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }






}
