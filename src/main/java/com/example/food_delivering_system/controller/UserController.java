package com.example.food_delivering_system.controller;


import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.dto.Request.PlaceOrderDTO;
import com.example.food_delivering_system.dto.Response.UserDTO;
import com.example.food_delivering_system.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/{userId}/completed-orders")
    public ResponseEntity<List<OrderDTO>> getAllCompletedOrders(@PathVariable Long userId) {
        List<OrderDTO> completedOrders = userServices.getAllCompletedOrders(userId);
        return new ResponseEntity<>(completedOrders, HttpStatus.OK);
    }

    @GetMapping("/{userId}/ongoing-orders")
    public ResponseEntity<List<OrderDTO>> getAllOngoingOrders(@PathVariable Long userId) {
        List<OrderDTO> ongoingOrders = userServices.getAllOngoingOrders(userId);
        return new ResponseEntity<>(ongoingOrders, HttpStatus.OK);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO user = userServices.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO userDTO) {

        // Not ABLE TO RETURN IF AVAILABLE AND THE USER ID

        UserDTO createdUser = userServices.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }



    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody CreateUserDTO userDTO) {
        UserDTO updatedUser = userServices.updateUser(userId, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping(value = "/place-order" , consumes = "application/json;charset=UTF-8")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
        OrderDTO order = userServices.createOrder(placeOrderDTO);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long userId, @PathVariable Long orderId) {
        OrderDTO updatedOrder = userServices.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userServices.deleteUser(userId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }



}




