package com.example.food_delivering_system.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "User_Table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String userName;
    private String password;
    private String email;
    private double latitude;
    private double longitude;
    private String phoneNo;

    private HashMap<Dish,Double> cart;

    @OneToMany(mappedBy = "user" ,fetch = FetchType.EAGER)
    private List<Order> order;

}
