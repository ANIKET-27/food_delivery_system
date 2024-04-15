package com.example.food_delivering_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static jakarta.persistence.FetchType.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name ="Order_Table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDateTime orderDate;
    private Integer orderStatus;
    private Double totalAmount;
    private Double latitude;
    private Double longitude;
    private String deliveryInstructions;
    private Long transactionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    private User driver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_id")
    )
    @MapKeyJoinColumn(name = "dish_id")
    @Column(name = "quantity")
    private Map<Dish, Double> orderItems;


    public void calculateAmt(){

        double total=0;

        for(Map.Entry<Dish,Double> e : orderItems.entrySet()){
            total += e.getValue() * e.getKey().getPrice();
        }

        setTotalAmount(total);

    }


}
