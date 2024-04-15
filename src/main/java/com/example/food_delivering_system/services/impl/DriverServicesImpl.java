package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.dto.Response.UserDTO;
import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.Role;
import com.example.food_delivering_system.entities.User;
import com.example.food_delivering_system.repository.OrderRepository;
import com.example.food_delivering_system.repository.UserRepository;
import com.example.food_delivering_system.services.DriverServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServicesImpl implements DriverServices {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public  DriverServicesImpl(UserRepository userRepository, OrderRepository orderRepository){
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }


    @Override
    public UserDTO createDriver(CreateUserDTO dto) {

        User user = User.builder()
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .phoneNo(dto.getPhoneNo())
                .accNo(dto.getAccNo())
                .available(true)
                .role(Role.builder().role_id(3).role_name("Driver").build())
                .build();

        User save =  userRepository.save(user);

        return Convetor.userToUserDto(save);


    }

    @Override
    public UserDTO updateDriver(Long id, CreateUserDTO userDTO) {

        User user = Convetor.reqToUser(userDTO);
        user.setRole(Role.builder().role_id(3).role_name("Driver").build());
        user.setUserId(id);
        User save = userRepository.save(user);

        return Convetor.userToUserDto(save);

    }

    @Override
    public UserDTO getDriverById(Long id) {
        Optional<User> driverOpt = userRepository.findById(id);

        if(driverOpt.isEmpty()) throw new RuntimeException("Cannot Find The Driver");

        return Convetor.userToUserDto(driverOpt.get());
    }

    @Override
    public List<OrderDTO> getPastOrder(Long id){

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()) throw new RuntimeException("Could not find the user.");

        User user = optionalUser.get();

        List<Order> orders = orderRepository.findCompletedOrdersByDriver(user);

        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order o : orders)
            orderDTOList.add(Convetor.orderToOrderDto(o));

        return orderDTOList;

    }

    @Override
    public List<OrderDTO> getCurrentOrder(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()) throw new RuntimeException("Could not find the user.");

        User user = optionalUser.get();

        List<Order> orders = orderRepository.findOngoingOrdersByDriver(user);

        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order o : orders)
            orderDTOList.add(Convetor.orderToOrderDto(o));

        return orderDTOList;

    }

    @Override
    public void assignDriverToOrder(Long orderId, Long driverId) {

        Optional<User> driver = userRepository.findById(driverId);
        Optional<Order> order = orderRepository.findById(orderId);

        if(driver.isEmpty() || order.isEmpty()) throw  new RuntimeException("Something went wrong with the assingment of driver");


//        if(driver.get().getAvailable()) {

            order.get().setDriver(driver.get());
            driver.get().setAvailable(false);

            orderRepository.save(order.get());
            userRepository.save(driver.get());

//        }
//        else
//            throw  new RuntimeException("Order has Already been cancelled.");
//

    }

    @Override
    public void updateStatusForDelivery(Long orderId, Integer status) {

        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isEmpty()) throw  new RuntimeException("Something went wrong to accept the order");

        order.get().setOrderStatus(status);

        if(status == 3){

            // MAKE THE DRIVER AVAILABLE
            // INITIATE THE TRANSACTIONS

        }

        orderRepository.save(order.get());

    }

    @Override
    public void deleteDriver(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }

}
