package com.example.food_delivering_system.controller;


import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.dto.Response.JwtResponseDTO;
import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.dto.Request.PlaceOrderDTO;
import com.example.food_delivering_system.dto.Response.UserDTO;
import com.example.food_delivering_system.entities.RefreshToken;
import com.example.food_delivering_system.entities.User;
import com.example.food_delivering_system.repository.UserRepository;
import com.example.food_delivering_system.services.UserServices;
import com.example.food_delivering_system.services.impl.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServices userServices;
    private  UserDTO userDTO;

    @Autowired
    public UserController(UserServices userServices) {

        this.userServices = userServices;

    }

    @GetMapping("/completed-orders")
    public ResponseEntity<List<OrderDTO>> getAllCompletedOrders() {
        setUserDto();
        List<OrderDTO> completedOrders = userServices.getAllCompletedOrders(userDTO.getUserId());
        return new ResponseEntity<>(completedOrders, HttpStatus.OK);
    }

    @GetMapping("/ongoing-orders")
    public ResponseEntity<List<OrderDTO>> getAllOngoingOrders() {
        setUserDto();
        List<OrderDTO> ongoingOrders = userServices.getAllOngoingOrders(userDTO.getUserId());
        return new ResponseEntity<>(ongoingOrders, HttpStatus.OK);
    }


    @PutMapping("/updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody CreateUserDTO updatedUserDTO) {
        setUserDto();
        UserDTO updatedUser = userServices.updateUser(userDTO.getUserId(), updatedUserDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping(value = "/place-order" )
    public ResponseEntity<OrderDTO> createOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
        setUserDto();
        placeOrderDTO.setUser_id(userDTO.getUserId());
        OrderDTO order = userServices.createOrder(placeOrderDTO);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder( @PathVariable Long orderId) {
        OrderDTO updatedOrder = userServices.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteAcc")
    public ResponseEntity<String> deleteUser() {
        setUserDto();

        userServices.deleteUser(userDTO.getUserId());
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

    void setUserDto(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        this.userDTO = userServices.getByUserName(authentication.getName());

    }



}




