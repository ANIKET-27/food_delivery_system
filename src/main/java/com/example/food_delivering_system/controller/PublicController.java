package com.example.food_delivering_system.controller;

import com.example.food_delivering_system.dto.Response.DishDTO;
import com.example.food_delivering_system.services.impl.AdminServicesImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    private  final AdminServicesImpl adminServices;

    public PublicController(AdminServicesImpl adminServices){
        this.adminServices = adminServices;
    }

    @GetMapping("/dishes")
    public ResponseEntity<List<DishDTO>> getAllDishes() {
        List<DishDTO> dishes = adminServices.getAllDishes();
        return ResponseEntity.ok(dishes);
    }


}
