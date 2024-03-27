package com.example.food_delivering_system.services;

import com.example.food_delivering_system.DTO.DriverDTO;
import com.example.food_delivering_system.DTO.OrderDTO;

import java.util.List;


public interface DriverServices {

    List<DriverDTO> getAllDrivers();

    List<DriverDTO>  getAllAvailableDrivers();

    DriverDTO createDriver(DriverDTO driverDTO);

    DriverDTO updateDriver(DriverDTO driverDTO);

    DriverDTO acceptOrder(DriverDTO driverDTO, OrderDTO orderDTO);

}
