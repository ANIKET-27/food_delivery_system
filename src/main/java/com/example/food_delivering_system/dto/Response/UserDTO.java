package com.example.food_delivering_system.dto.Response;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;
    private String userName;
    private String password;
    private String email;
    private Double latitude;
    private Double longitude;
    private String phoneNo;
    private Long accNo;
    private Boolean available;

}
