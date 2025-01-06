package com.example.food_delivering_system.controller;


import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.dto.Response.JwtResponseDTO;
import com.example.food_delivering_system.entities.RefreshToken;
import com.example.food_delivering_system.services.impl.UserServicesImpl;
import com.example.food_delivering_system.services.impl.security.JwtService;
import com.example.food_delivering_system.services.impl.security.RefreshTokenService;
import com.example.food_delivering_system.services.impl.security.UserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthorizeCallback;

@AllArgsConstructor
@RestController
@RequestMapping("public/signup")
public class AuthController
{
    private final JwtService jwtService;
    private final  RefreshTokenService refreshTokenService;
    private final UserDetailServiceImpl userDetailsService;

    @Autowired
    public AuthController (UserDetailServiceImpl userDetailsService, RefreshTokenService refreshTokenService, JwtService jwtService){
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/user")
    public ResponseEntity SignUpUser(@RequestBody CreateUserDTO userInfoDto){
        try{
            Boolean isSignUped = userDetailsService.signupUser(userInfoDto);
            if(Boolean.FALSE.equals(isSignUped)){
                return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
            }


            return new ResponseEntity<>("User successfully created", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Exception in User Service", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/driver")
    public ResponseEntity SignUpDriver(@RequestBody CreateUserDTO userInfoDto){
        try{
            Boolean isSignUped = userDetailsService.signupDriver(userInfoDto);
            if(Boolean.FALSE.equals(isSignUped)){
                return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>("Driver successfully created",HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Exception in User Service", HttpStatus.UNAUTHORIZED);
        }
    }


}
