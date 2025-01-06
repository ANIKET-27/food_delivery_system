package com.example.food_delivering_system.entities;

import jakarta.persistence.Entity;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverLocation {

    private double latitude;
    private double longitude;

}
