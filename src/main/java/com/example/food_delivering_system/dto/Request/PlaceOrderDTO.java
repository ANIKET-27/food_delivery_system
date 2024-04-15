package com.example.food_delivering_system.dto.Request;

import lombok.*;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PlaceOrderDTO {


    private HashMap<Long,Double> cart;
    private String deliveryInstructions;
    private Long user_id;

}
