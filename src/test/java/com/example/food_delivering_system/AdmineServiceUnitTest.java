package com.example.food_delivering_system;

import com.example.food_delivering_system.dto.Response.UserDTO;
import com.example.food_delivering_system.services.impl.AdminServicesImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AdmineServiceUnitTest {

    @Autowired
    private AdminServicesImpl adminServices;



    @Test
    void getAllCustormers(){

        List<UserDTO> customers =  adminServices.getAllCustomers();

        for(UserDTO u : customers) System.out.println(u);

    }

    @Test
    void createDish(){

        adminServices.createDish("Momos","Tibet_Style",40.0);
        adminServices.createDish("Noodles","Soggy",80.0);
        adminServices.createDish("Jalabi","Crispy",200.0);
        adminServices.createDish("Dosa","South Indian",100.0);

    }


}
