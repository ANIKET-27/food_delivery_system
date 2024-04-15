package com.example.food_delivering_system.dto.Response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DishDTO {

    private Long dish_id;
    private String name;
    private String description;
    private Double price;

}
