package com.example.food_delivering_system.dto.Request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDishDTO {

    private long dish_id;
    private String name;
    private String description;
    private double price;

}
