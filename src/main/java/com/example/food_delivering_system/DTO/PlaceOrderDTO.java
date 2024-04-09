package com.example.food_delivering_system.DTO;

import com.example.food_delivering_system.entities.Dish;
import lombok.*;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PlaceOrderDTO {

    private HashMap<Dish,Double> cart;
    private String deliveryInstructions;
    private long user_id;

}
