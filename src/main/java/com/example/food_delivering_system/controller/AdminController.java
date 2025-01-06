package com.example.food_delivering_system.controller;


import com.example.food_delivering_system.dto.Request.CreateDishDTO;
import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.dto.Response.*;
import com.example.food_delivering_system.entities.RefreshToken;
import com.example.food_delivering_system.services.impl.AdminServicesImpl;
import com.example.food_delivering_system.services.impl.security.JwtService;
import com.example.food_delivering_system.services.impl.security.RefreshTokenService;
import com.example.food_delivering_system.services.impl.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminServicesImpl adminServices;
    private final UserDetailServiceImpl userDetailsService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AdminController(AdminServicesImpl adminServices, UserDetailServiceImpl userDetailsService, JwtService jwtService, RefreshTokenService refreshTokenService) {
        this.adminServices = adminServices;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
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


    @GetMapping("/dishes/{id}")
    public ResponseEntity<DishDTO> getDishById(@PathVariable Long id) {
        DishDTO dish = adminServices.getDishById(id);
        return ResponseEntity.ok(dish);
    }

    @PostMapping("/dishes")
    public ResponseEntity<DishDTO> createDish(@RequestBody DishDTO dto) {
        DishDTO dish = adminServices.createDish(dto);
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

    @PostMapping("/admin")
    public ResponseEntity SignUpAdmin(@RequestBody CreateUserDTO userInfoDto){
        try{
            Boolean isSignUped = userDetailsService.signupAdmin(userInfoDto);
            if(Boolean.FALSE.equals(isSignUped)){
                return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
            }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUserName());
            String jwtToken = jwtService.GenerateToken(userInfoDto.getUserName());
            return new ResponseEntity<>(JwtResponseDTO.builder().accessToken(jwtToken).
                    token(refreshToken.getToken()).build(), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
