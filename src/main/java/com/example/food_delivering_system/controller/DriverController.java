package com.example.food_delivering_system.controller;

import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.dto.Response.UserDTO;
import com.example.food_delivering_system.services.DriverServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverServices driverServices;

    @Autowired
    public DriverController(DriverServices driverServices) {
        this.driverServices = driverServices;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getDriverById(@PathVariable Long id) {
        UserDTO driver = driverServices.getDriverById(id);
        return ResponseEntity.ok(driver);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createDriver(@RequestBody CreateUserDTO dto) {
        UserDTO driver = driverServices.createDriver(dto);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateDriver(@PathVariable Long id, @RequestBody CreateUserDTO userDTO) {
        UserDTO updatedDriver = driverServices.updateDriver(id,userDTO);
        return ResponseEntity.ok(updatedDriver);
    }

    @GetMapping("/{id}/orders/past")
    public ResponseEntity<List<OrderDTO>> getPastOrders(@PathVariable Long id) {
        List<OrderDTO> pastOrders = driverServices.getPastOrder(id);
        return ResponseEntity.ok(pastOrders);
    }

    @GetMapping("/{id}/orders/current")
    public ResponseEntity<List<OrderDTO>> getCurrentOrders(@PathVariable Long id) {
        List<OrderDTO> currentOrders = driverServices.getCurrentOrder(id);
        return ResponseEntity.ok(currentOrders);
    }

    @PostMapping("/assign/{orderId}/{driverId}")
    public ResponseEntity<String> assignDriverToOrder(@PathVariable Long orderId, @PathVariable Long driverId) {
        driverServices.assignDriverToOrder(orderId, driverId);
        return ResponseEntity.ok("Driver assigned to order successfully");
    }

    @PutMapping("/orders/{orderId}/status/{status}")
    public ResponseEntity<String> updateStatusForDelivery(@PathVariable Long orderId, @PathVariable Integer status) {
        driverServices.updateStatusForDelivery(orderId, status);
        return ResponseEntity.ok("Order status updated successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteDriver(@PathVariable Long userId) {
        driverServices.deleteDriver(userId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }


}
