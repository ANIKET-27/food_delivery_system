package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.dto.Request.PlaceOrderDTO;
import com.example.food_delivering_system.dto.Response.UserDTO;
import com.example.food_delivering_system.entities.Dish;
import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.Role;
import com.example.food_delivering_system.entities.User;
import com.example.food_delivering_system.repository.DishRepository;
import com.example.food_delivering_system.repository.OrderRepository;
import com.example.food_delivering_system.repository.UserRepository;
import com.example.food_delivering_system.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private  final DishRepository dishRepository;

    @Autowired
    public UserServicesImpl(UserRepository userRepository, OrderRepository orderRepository, DishRepository dishRepository){
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;

    }



    @Override
    public List<OrderDTO> getAllOngoingOrders(Long id) {
        List<OrderDTO> list = new ArrayList<>();

        List<Order> orders = orderRepository.findOngoingOrdersByUser(getUser(id));

        if(orders == null) throw new RuntimeException("ORDERS ARE NOT FETCHED PROPERLY");

        for (Order order : orders) list.add(Convetor.orderToOrderDto(order));

        return list;
    }

    @Override
    public List<OrderDTO> getAllCompletedOrders(Long id) {
        List<OrderDTO> list = new ArrayList<>();


        List<Order> orders = orderRepository.findCompletedOrdersByUser(getUser(id));

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
    public UserDTO createUser(CreateUserDTO dto) {

        User user = User.builder()
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .phoneNo(dto.getPhoneNo())
                .available(false)
                .accNo(dto.getAccNo())
                .role(Role.builder().role_id(2).role_name("User").build())
                .build();

        User save =  userRepository.save(user);

        return Convetor.userToUserDto(save);

    }

    @Override
    public UserDTO updateUser(Long userId, CreateUserDTO userDTO) {

        User user = Convetor.reqToUser(userDTO);
        user.setUserId(userId);
        user.setRole(Role.builder().role_id(2).role_name("User").build());
        User save = userRepository.save(user);

        return Convetor.userToUserDto(save);

    }

    @Override
    public OrderDTO createOrder(PlaceOrderDTO placeOrderDTO) {

        Optional<User> optUser = userRepository.findById(placeOrderDTO.getUser_id());



        if(optUser.isEmpty())
            throw new RuntimeException("User not found");

        User user = optUser.get();

        Order newOrder = Order.builder()
                .orderDate(LocalDateTime.now())
                .user(user)
                .orderStatus(1)
                .latitude(user.getLatitude())
                .longitude(user.getLongitude())
                .deliveryInstructions(placeOrderDTO.getDeliveryInstructions())
                .orderItems(new HashMap<>())
                .build();

        for(Map.Entry<Long,Double> e : placeOrderDTO.getCart().entrySet()) {

            Optional<Dish> dish = dishRepository.findById(e.getKey());

            if(dish.isEmpty()) throw new RuntimeException("Ordered Item Is Mot Found");

            newOrder.getOrderItems().put(dish.get(), e.getValue());

        }

        newOrder.calculateAmt();

        Order save = orderRepository.save(newOrder);

        return Convetor.orderToOrderDto(save);

    }

    @Override
    public OrderDTO cancelOrder(Long orderId) {
         Optional<Order> optionalOrder = orderRepository.findById(orderId);

         if(optionalOrder.isEmpty()) throw  new RuntimeException("Order Do Not Exist");

         Order order = optionalOrder.get();

         if(order.getOrderStatus() == -1) throw new RuntimeException("Order Has Already Being Cancelled");

         order.setOrderStatus(-1);


            // MAKE THE DRIVER AVAILABLE
            // INITIATE THE TRANSACTIONS



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

    public  User getUser(Long id){
        Optional<User> optUser = userRepository.findById(id);

        if(optUser.isEmpty()) throw new RuntimeException("USER NOT FOUND");

        return optUser.get();

    }


}
