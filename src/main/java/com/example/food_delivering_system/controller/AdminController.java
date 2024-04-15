package com.example.food_delivering_system.controller;


import com.example.food_delivering_system.dto.Request.CreateDishDTO;
import com.example.food_delivering_system.dto.Response.DishDTO;
import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.dto.Response.RoleDTO;
import com.example.food_delivering_system.dto.Response.UserDTO;
import com.example.food_delivering_system.services.impl.AdminServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminServicesImpl adminServices;

    @Autowired
    public AdminController(AdminServicesImpl adminServices) {
        this.adminServices = adminServices;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = adminServices.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<UserDTO>> getAllCustomers() {
        List<UserDTO> customers = adminServices.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = adminServices.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/roles")
    public ResponseEntity<Void> addRole(@RequestBody RoleDTO roleDto) {
        adminServices.addRole(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDto) {
        RoleDTO updatedRole = adminServices.updateRole(id,roleDto);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        adminServices.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dishes")
    public ResponseEntity<List<DishDTO>> getAllDishes() {
        List<DishDTO> dishes = adminServices.getAllDishes();
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/dishes/{id}")
    public ResponseEntity<DishDTO> getDishById(@PathVariable Long id) {
        DishDTO dish = adminServices.getDishById(id);
        return ResponseEntity.ok(dish);
    }

    @PostMapping("/dishes")
    public ResponseEntity<DishDTO> createDish(@RequestBody CreateDishDTO dto) {
        DishDTO dish = adminServices.createDish(dto.getName(), dto.getDescription(), dto.getPrice());
        return new ResponseEntity<>(dish, HttpStatus.CREATED);
    }

    @PutMapping("/dishes/{id}")
    public ResponseEntity<DishDTO> updateDish(@PathVariable Long id, @RequestBody DishDTO dishDTO) {

        DishDTO updatedDish = adminServices.updateDish(dishDTO);
        return ResponseEntity.ok(updatedDish);
    }

    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        adminServices.deleteDish(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = adminServices.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/ongoing")
    public ResponseEntity<List<OrderDTO>> getOngoingOrders() {
        List<OrderDTO> orders = adminServices.getOngoingOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/completed")
    public ResponseEntity<List<OrderDTO>> getCompletedOrders() {
        List<OrderDTO> orders = adminServices.getCompletedOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<UserDTO>> getAllDrivers() {
        List<UserDTO> drivers = adminServices.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/drivers/unavailable")
    public ResponseEntity<List<UserDTO>> getAllUnavailableDrivers() {
        List<UserDTO> drivers = adminServices.getAllUnavailableDrivers();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/drivers/available")
    public ResponseEntity<List<UserDTO>> getAllAvailableDrivers() {
        List<UserDTO> drivers = adminServices.getAllAvailableDrivers();
        return ResponseEntity.ok(drivers);
    }
}
