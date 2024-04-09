package com.example.food_delivering_system.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driver_id;
    private String username;
    private String password;
    private String email;
    private double latitude;
    private double longitude;
    private String phoneNo;
    private Boolean available;

    @OneToMany(mappedBy = "driver" , fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

}
