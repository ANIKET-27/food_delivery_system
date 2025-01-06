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
        roleRepository.save(Role.builder().roleId(1).roleName("Admine").build());
        roleRepository.save( Role.builder().roleId(2).roleName("User").build());
        roleRepository.save(Role.builder().roleId(3).roleName("Driver").build());
    }

}
