package com.example.food_delivering_system.DTO;

import com.example.food_delivering_system.entities.Location;
import com.example.food_delivering_system.entities.Order;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    public Long id;
    public String username;
    public String password;
    private String email;

    private Location location;
    private String phoneNo;
    private Boolean available;
    private List<Order> orders = new ArrayList<>();
}
