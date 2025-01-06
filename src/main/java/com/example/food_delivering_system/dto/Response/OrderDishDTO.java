package com.example.food_delivering_system.dto.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDishDTO {

    private Long dish_id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private String url;
    private double quantity;

}
