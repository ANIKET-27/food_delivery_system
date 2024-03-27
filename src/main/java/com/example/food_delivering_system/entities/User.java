package com.example.food_delivering_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String password;
    private String email;
    private Location location;
    private String phoneNo;

    private HashMap<Dish,Double> cart = new HashMap<>();

    @OneToMany(mappedBy = "user")
    private List<Order> order = new ArrayList<>();

}
