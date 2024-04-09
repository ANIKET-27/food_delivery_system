package com.example.food_delivering_system.services;

import com.example.food_delivering_system.DTO.DriverDTO;
import com.example.food_delivering_system.DTO.OrderDTO;

import java.util.List;


public interface DriverServices {

    List<DriverDTO> getAllDrivers();

    List<DriverDTO>  getAllAvailableDrivers();

    DriverDTO createDriver(DriverDTO driverDTO);

    DriverDTO getDriverById(Long id);

    DriverDTO updateDriver(DriverDTO driverDTO);

    List<OrderDTO> getPastOrder(Long id);

    List<OrderDTO> getCurrentOrder(Long id);

    void assignDriverToOrder (Long orderId, Long driverId);

    void updateStatusForDelivery(Long orderId, String str);



}
