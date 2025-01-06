package com.example.food_delivering_system.services.impl.security;

import com.example.food_delivering_system.entities.RefreshToken;
import com.example.food_delivering_system.entities.User;
import com.example.food_delivering_system.repository.RefreshTokenRepository;
import com.example.food_delivering_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private  final RefreshTokenRepository refreshTokenRepository;
    private  final UserRepository userRepository;


    public RefreshTokenService(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {

        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;

    }



    public RefreshToken createRefreshToken(String username) {
        User  user = null;
        try {
            user = userRepository.findByUserName(username);
        }
        catch (Exception e){
            System.out.println(e);
        }

        Optional<RefreshToken> alreadyExisted = refreshTokenRepository.findByUserInfo(user);

        if(alreadyExisted.isPresent()){

            RefreshToken token = alreadyExisted.get();

            if(!verifyExpiration(token)) return  token;
        }

        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(user)
                .token(UUID.randomUUID().toString())
                .expireDate(Instant.now().plusMillis(1000 * 60 * 60 * 4))
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    public boolean verifyExpiration(RefreshToken token) {

        if (token.getExpireDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
             return true;
        }

        return false;

    }


}
