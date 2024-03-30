package com.example.food_delivering_system.entities;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Location {
    private  double latitude;
    private double longitude;
}
