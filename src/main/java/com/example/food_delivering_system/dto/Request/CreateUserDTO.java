package com.example.food_delivering_system.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {

    private String userName;
    private String password;
    private String email;
    private Double latitude;
    private Double longitude;
    private String phoneNo;
    private Boolean available;
    private Long accNo;

}
