package com.example.food_delivering_system.DTO;

import com.example.food_delivering_system.entities.Dish;
import com.example.food_delivering_system.entities.Driver;
import com.example.food_delivering_system.entities.Location;
import com.example.food_delivering_system.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {
    private Long orderId;
    private LocalDateTime orderDate;
    private String status;
    private double totalAmount;
    private Location deliveryLocation;
    private String deliveryInstructions;
    private String paymentStatus;


    private Driver driver;


    private User user;


    private Map<Dish, Double> orderItems = new HashMap<>();

}
