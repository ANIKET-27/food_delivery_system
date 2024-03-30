package com.example.food_delivering_system.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String password;
    private String email;
    private double latitude;
    private double longitude;
    private String phoneNo;

    private HashMap<Dish,Double> cart = new HashMap<>();

    @OneToMany(mappedBy = "user")
    private List<Order> order = new ArrayList<>();

}
