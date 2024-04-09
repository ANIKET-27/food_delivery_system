package com.example.food_delivering_system.DTO;

import com.example.food_delivering_system.entities.Dish;

import com.example.food_delivering_system.entities.Order;

import lombok.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private long user_id;
    private String userName;
    private String password;
    private String email;
    private double latitude;
    private double longitude;
    private String phoneNo;

    private HashMap<Dish,Double> cart;


    private List<Order> order;

}
