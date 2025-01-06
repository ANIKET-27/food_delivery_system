package com.example.food_delivering_system.dto.Response;

import com.example.food_delivering_system.entities.Dish;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class OrderDTO {


    private Long orderId;
    private LocalDateTime orderDate;
    private Integer orderStatus;
    private Double totalAmount;
    private Double latitude;
    private Double longitude;
    private String deliveryInstructions;
    private Long transactionId;
    private String customerName;
    private String driverName;
    private Long driverId;
    private Long userId;
    private String driverPhNo;
    private String customerPhNo;

    private ArrayList<OrderDishDTO> orderItems;


}
