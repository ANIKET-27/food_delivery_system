package com.example.food_delivering_system.services;

import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.dto.Response.UserDTO;

import java.util.List;


public interface DriverServices {


    UserDTO createDriver(CreateUserDTO dto);

    UserDTO getDriverById(Long id);

    UserDTO updateDriver(Long id, CreateUserDTO driverDTO);

    List<OrderDTO> getPastOrder(Long id);

    List<OrderDTO> getAvailableOrder();

    List<OrderDTO> getCurrentOrder(Long id);

    void assignDriverToOrder (Long orderId, Long driverId);

    void updateStatusForDelivery(Long orderId, Integer status);

    void deleteDriver(Long id);

}
