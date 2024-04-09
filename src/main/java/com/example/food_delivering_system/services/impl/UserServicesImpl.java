package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.DTO.OrderDTO;
import com.example.food_delivering_system.DTO.PlaceOrderDTO;
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
        List<UserDTO> list = new ArrayList<>();
        for(User u : userRepository.findAll()) list.add(Convetor.userToUserDto(u));

        return list;

    }

    @Override
    public List<OrderDTO> getAllOngoingOrders(Long id) {
        List<OrderDTO> list = new ArrayList<>();

        User user = Convetor.userDtoToUser(getUserById(id));

        List<Order> orders = orderRepository.findOngoingOrdersByUser(user);

        if(orders == null) throw new RuntimeException("ORDERS ARE NOT FETCHED PROPERLY");

        for (Order order : orders) list.add(Convetor.orderToOrderDto(order));

        return list;
    }

    @Override
    public List<OrderDTO> getAllCompletedOrders(Long id) {
        List<OrderDTO> list = new ArrayList<>();

        User user = Convetor.userDtoToUser(getUserById(id));

        List<Order> orders = orderRepository.findCompletedOrdersByUser(user);

        if(orders == null) throw new RuntimeException("ORDERS ARE NOT FETCHED PROPERLY");

        for (Order order : orders) list.add(Convetor.orderToOrderDto(order));

        return list;
    }

    @Override
    public UserDTO getUserById(Long id) {

        Optional<User> optUser = userRepository.findById(id);

        if(optUser.isEmpty()) throw new RuntimeException("USER NOT FOUND");

        User user = optUser.get();

        return  Convetor.userToUserDto(user);

    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = Convetor.userDtoToUser(userDTO);

        User save =  userRepository.save(user);
        return Convetor.userToUserDto(save);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user = Convetor.userDtoToUser(userDTO);
        userRepository.save(user);
        return userDTO;
    }

    @Override
    public UserDTO createOrder(PlaceOrderDTO placeOrderDTO) {
        UserDTO user = getUserById(placeOrderDTO.getUser_id());

        Order newOrder = Order.builder()
                .orderDate(LocalDateTime.now())
                .user(Convetor.userDtoToUser(user))
                .orderStatus("Ongoing")
                .deliveryInstructions(placeOrderDTO.getDeliveryInstructions())
                .paymentStatus("Cash On Delivery")
                .orderItems(placeOrderDTO.getCart())
                .build();

        newOrder.calculateAmt();

        Order save = orderRepository.save(newOrder);

        user.getOrder().add(save);

        return updateUser(user);

    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
         Optional<Order> optionalOrder = orderRepository.findById(orderDTO.getOrderId());
         if(optionalOrder.isEmpty()) throw  new RuntimeException("Order Do Not Exist");

         Order order = optionalOrder.get();

         //if(order.getOrderStatus() == null || order.getOrderStatus().equals("Canclled")) throw new RuntimeException("Order Has Already Being Cancelled");

         order.setOrderStatus("Canceled");

         Order saved = orderRepository.save(order);

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
