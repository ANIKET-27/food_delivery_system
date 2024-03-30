package com.example.food_delivering_system.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static jakarta.persistence.FetchType.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDateTime orderDate;
    private String orderStatus;
    private double totalAmount;
    private double latitude;
    private double longitude;
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
    @MapKeyJoinColumn(name = "dish_id")
    @Column(name = "quantity")
    private Map<Dish, Double> orderItems = new HashMap<>();

    public void calculateAmt(){
        double total=0;

        for(Map.Entry<Dish,Double> e : orderItems.entrySet()){
            total += e.getValue() * e.getKey().getPrice();
        }

        setTotalAmount(total);

    }


}
