package com.example.food_delivering_system;


import com.example.food_delivering_system.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.food_delivering_system.entities.Role;

@SpringBootTest
public class RoleServiceUnitTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    void createRote(){
        roleRepository.save(Role.builder().role_id(1).role_name("Admine").build());
        roleRepository.save( Role.builder().role_id(2).role_name("User").build());
        roleRepository.save(Role.builder().role_id(3).role_name("Driver").build());
    }

}
