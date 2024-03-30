package com.example.food_delivering_system.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String username;
    public String password;
    private String email;
    private double latitude;
    private double longitude;
    private String phoneNo;
    private Boolean available;

    @OneToMany(mappedBy = "driver")
    private List<Order> orders = new ArrayList<>();



}
