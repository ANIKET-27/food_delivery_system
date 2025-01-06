package com.example.food_delivering_system;


import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.dto.Request.PlaceOrderDTO;
import com.example.food_delivering_system.entities.User;
import com.example.food_delivering_system.repository.UserRepository;
import com.example.food_delivering_system.services.impl.DriverServicesImpl;
import com.example.food_delivering_system.services.impl.UserServicesImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class UserServiceIUnitTest {

    @Autowired
    private UserServicesImpl userServices;
    @Autowired
    private DriverServicesImpl driverServices;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findUserByUserName(){
        User user = userRepository.findByUserName("john_doe");
        System.out.println(user.getEmail());
    }

    @Test
    void createUser(){

        userServices.createUser(
                CreateUserDTO.builder()
                        .userName("Admin1")
                        .password("password")
                        .email("admin@ex.com")
                        .phoneNo("239439")
                        .latitude(234.4)
                        .longitude(23.5)
                        .accNo(2L)
                        .available(false)
                        .build()
        );

    }

    @Test
    void createDriver(){

        userServices.createUser(
                CreateUserDTO.builder()
                        .userName("Driver2")
                        .password("password")
                        .email("driver2@ex.com")
                        .phoneNo("239439")
                        .latitude(234.4)
                        .longitude(23.5)
                        .accNo(3L)
                        .available(true)
                        .build()
        );

    }

    @Test
    void deleteUser(){
        userServices.deleteUser(5L);
    }




    @Test
    void getUser(){
        Long id = 6L;
        userServices.getUserById(id);
    }


    @Test
    void userCreatingOrder(){

        PlaceOrderDTO placeOrderDTO = PlaceOrderDTO.builder()
                .cart(new HashMap<>())
                .deliveryInstructions("Can you make it Lightning Fast?")
                .user_id(1L)
                .build();



        placeOrderDTO.getCart().put(4L,1.0);
//        placeOrderDTO.getCart().put(2L,2.0);

        userServices.createOrder(placeOrderDTO);

    }



    @Test
    void cancelOrder(){

        userServices.cancelOrder(3L);


    }

    @Test
    void getUserOngoingOrder(){
        List<OrderDTO> ongoing = userServices.getAllOngoingOrders(1L);

        for(OrderDTO o : ongoing) System.out.println(o);

    }


    @Test
    void getUserCompletedOrder(){

        List<OrderDTO> completed = userServices.getAllCompletedOrders(1L);

        for(OrderDTO e : completed )  System.out.println(e);

    }


    @Test
    void testAssignDriverToOrder(){

        driverServices.assignDriverToOrder(4L,4L);

    }

}
