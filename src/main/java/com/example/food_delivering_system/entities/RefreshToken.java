package com.example.food_delivering_system.entities;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "Tokens")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String token;

    private Instant expireDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userInfo;

    public String getTokenOwnerRole(){
        return userInfo.getUserRoleName();
    }

    public String getTokenOwnerName(){
        return userInfo.getUserName();
    }

}
