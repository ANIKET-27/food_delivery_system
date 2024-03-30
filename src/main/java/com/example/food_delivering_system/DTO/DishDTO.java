package com.example.food_delivering_system.DTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishDTO {
    private long id;
    private String name;
    private String description;
    private double price;
}
