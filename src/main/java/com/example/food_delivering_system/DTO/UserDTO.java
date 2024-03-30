package com.example.food_delivering_system.DTO;

import com.example.food_delivering_system.entities.Dish;
import com.example.food_delivering_system.entities.Location;
import com.example.food_delivering_system.entities.Order;
import lombok.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private long id;
    private String userName;
    private String password;
    private String email;
    private Location location;
    private String phoneNo;

    private HashMap<Dish,Double> cart = new HashMap<>();

    private List<Order> order = new ArrayList<>();
    
}
