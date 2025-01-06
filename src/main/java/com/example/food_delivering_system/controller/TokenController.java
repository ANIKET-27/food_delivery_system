package com.example.food_delivering_system.controller;


import ch.qos.logback.core.subst.Token;
import com.example.food_delivering_system.dto.Request.AuthRequestDTO;
import com.example.food_delivering_system.dto.Request.RefreshTokenDTO;
import com.example.food_delivering_system.dto.Response.JwtResponseDTO;
import com.example.food_delivering_system.entities.RefreshToken;
import com.example.food_delivering_system.entities.User;
import com.example.food_delivering_system.services.impl.security.JwtService;
import com.example.food_delivering_system.services.impl.security.RefreshTokenService;
import org.hibernate.boot.model.internal.CannotForceNonNullableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("public")
public class TokenController
{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUserName(),
                    authRequestDTO.getPassword()));

            if (authentication.isAuthenticated()) {
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUserName());
                return new ResponseEntity<>(JwtResponseDTO.builder()
                        .accessToken(jwtService.GenerateToken(authRequestDTO.getUserName()))
                        .token(refreshToken.getToken())
                        .role(refreshToken.getTokenOwnerRole())
                        .build(), HttpStatus.OK);

            } else {
                return new ResponseEntity<>("Incorrect credential.", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            return  new ResponseEntity<>("Internal server error.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponseDTO> refreshToken(@RequestBody RefreshTokenDTO refreshTokenRequestDTO){
//        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
//                .map(token -> refreshTokenService.verifyExpiration(token))
//                .map(RefreshToken::getUserInfo)
//                .map(userInfo -> {
//                    String accessToken = jwtService.GenerateToken(userInfo.getUserName());
//                    return JwtResponseDTO.builder()
//                            .accessToken(accessToken)
//                            .token(refreshTokenRequestDTO.getToken()).build();
//                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));

        try {

            Optional<RefreshToken> optionalRefreshToken = refreshTokenService.findByToken(refreshTokenRequestDTO.getToken());

            if(optionalRefreshToken.isEmpty()) throw new Exception("Token Not In DB");

            RefreshToken refreshToken = optionalRefreshToken.get();

            if(refreshTokenService.verifyExpiration(refreshToken)) throw new Exception("Token Expired");

            String accessToken = jwtService.GenerateToken(refreshToken.getTokenOwnerName());

            return  new ResponseEntity(JwtResponseDTO.builder()
                    .accessToken(accessToken)
                    .token(refreshToken.getToken())
                    .role(refreshToken.getTokenOwnerRole())
                    .build()
                    ,HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity("Please Make A Login", HttpStatus.UNAUTHORIZED);
        }
    }

}
