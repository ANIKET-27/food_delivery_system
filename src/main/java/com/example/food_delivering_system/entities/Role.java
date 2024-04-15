package com.example.food_delivering_system.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    private Integer role_id;
    private String role_name;

}
