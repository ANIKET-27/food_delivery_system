package com.example.food_delivering_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static jakarta.persistence.FetchType.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDateTime orderDate;
    private String status;
    private double totalAmount;
    private Location deliveryLocation;
    private String deliveryInstructions;
    private String paymentStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    @CollectionTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_id")
    )
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Dish, Double> orderItems = new HashMap<>();


}
