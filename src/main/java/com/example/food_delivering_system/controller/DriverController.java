package com.example.food_delivering_system.controller;

import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.dto.Response.UserDTO;
import com.example.food_delivering_system.entities.DriverLocation;
import com.example.food_delivering_system.services.DriverServices;
import com.example.food_delivering_system.services.impl.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverServices driverServices;
    private final UserServicesImpl userServices;
    private  UserDTO userDTO;

    @Autowired
    public DriverController(UserServicesImpl userServices, DriverServices driverServices) {
        this.driverServices = driverServices;
        this.userServices = userServices;
    }


    @PostMapping
    public ResponseEntity<UserDTO> createDriver(@RequestBody CreateUserDTO dto) {
        UserDTO driver = driverServices.createDriver(dto);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

    @PutMapping("/updateDriver")
    public ResponseEntity<UserDTO> updateDriver(@RequestBody CreateUserDTO createUserDTO) {
        setUserDto();
        UserDTO updatedDriver = driverServices.updateDriver(userDTO.getUserId(), createUserDTO);
        return ResponseEntity.ok(updatedDriver);
    }

    @GetMapping("/completed-orders")
    public ResponseEntity<List<OrderDTO>> getPastOrders() {
        setUserDto();
        List<OrderDTO> pastOrders = driverServices.getPastOrder(userDTO.getUserId());
        return ResponseEntity.ok(pastOrders);
    }

    @GetMapping("/availableOrder")
    public ResponseEntity<List<OrderDTO>> getAvailable() {
        List<OrderDTO> orders = driverServices.getAvailableOrder();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/ongoing-orders")
    public ResponseEntity<List<OrderDTO>> getCurrentOrders() {
        setUserDto();
        List<OrderDTO> currentOrders = driverServices.getCurrentOrder(userDTO.getUserId());
        return ResponseEntity.ok(currentOrders);
    }

    @PostMapping("/assign/{orderId}")
    public ResponseEntity<String> assignDriverToOrder(@PathVariable Long orderId) {
        setUserDto();
        try{
            driverServices.assignDriverToOrder(orderId, userDTO.getUserId());
            return ResponseEntity.ok("Driver assigned to order successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PutMapping("/updateOrder/{orderId}/{status}")
    public ResponseEntity<String> updateStatusForDelivery(@PathVariable Long orderId, @PathVariable Integer status) {
        driverServices.updateStatusForDelivery(orderId, status);
        return ResponseEntity.ok("Order status updated successfully");
    }

    @DeleteMapping("/deleteAcc")
    public ResponseEntity<String> deleteDriver() {
        setUserDto();
        driverServices.deleteDriver(userDTO.getUserId());
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

    void setUserDto(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        this.userDTO = userServices.getByUserName(authentication.getName());

    }


}
