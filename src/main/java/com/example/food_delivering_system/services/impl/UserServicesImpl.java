package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.DTO.OrderDTO;
import com.example.food_delivering_system.DTO.UserDTO;
import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.User;
import com.example.food_delivering_system.repository.OrderRepository;
import com.example.food_delivering_system.repository.UserRepository;
import com.example.food_delivering_system.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public UserServicesImpl(UserRepository userRepository, OrderRepository orderRepository){
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;

    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public List<OrderDTO> getAllOngoingOrders(Long id) {
        List<OrderDTO> list = new ArrayList<>();
        List<Order> orders = orderRepository.findOngoingOrdersByUserId(id);

        if(orders == null) throw new RuntimeException("ORDERS ARE NOT FETCHED PROPERLY");

        for (Order order : orders) list.add(Convetor.orderToOrderDto(order));

        return list;
    }

    @Override
    public List<OrderDTO> getAllCompletedOrders(Long id) {
        List<OrderDTO> list = new ArrayList<>();
        List<Order> orders = orderRepository.findCompletedOrdersByUserId(id);

        if(orders == null) throw new RuntimeException("ORDERS ARE NOT FETCHED PROPERLY");

        for (Order order : orders) list.add(Convetor.orderToOrderDto(order));

        return list;
    }

    @Override
    public UserDTO getUserById(Long id) {

        Optional<User> optUser = userRepository.findById(id);

        if(optUser.isEmpty()) throw new RuntimeException("USER NOT FOUND");

        return  Convetor.userToUserDto(optUser.get());

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
        userRepository.save(user);
        return userDTO;
    }

    @Override
    public UserDTO createOrder(UserDTO userDTO,String instruction) {

        if(userDTO.getCart().isEmpty()) throw new RuntimeException("CART IS EMPTY");

        Optional<User> optionalUser = userRepository.findById(userDTO.getId());

        if(optionalUser.isEmpty()) throw  new RuntimeException("User Not Logged In");

        User user = optionalUser.get();

        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(userDTO.getCart());
        order.calculateAmt();
        order.setOrderDate(LocalDateTime.now());
        order.setLatitude(user.getLatitude());
        order.setLongitude(user.getLatitude());
        order.setDeliveryInstructions(instruction);

        Order saveOrder = orderRepository.save(order);

        user.getOrder().add(saveOrder);

        User saveUser = userRepository.save(user);

        return Convetor.userToUserDto(saveUser);

    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
         Optional<Order> optionalOrder = orderRepository.findById(orderDTO.getOrderId());
         if(optionalOrder.isEmpty()) throw  new RuntimeException("Order Do Not Exist");

         Order order = optionalOrder.get();

         if(order.getOrderStatus().equals("Canclled")) throw new RuntimeException("Order Has Already Being Cancelled");

         Order saved = orderRepository.save(Convetor.orderDtoToOrder(orderDTO));

         return Convetor.orderToOrderDto(saved);

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
