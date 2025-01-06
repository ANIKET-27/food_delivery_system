package com.example.food_delivering_system.repository;

import com.example.food_delivering_system.entities.RefreshToken;
import com.example.food_delivering_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);


  //  @Query("SELECT t FROM Tokens t WHERE t.userInfo = user")
    Optional<RefreshToken> findByUserInfo(User user);

}
