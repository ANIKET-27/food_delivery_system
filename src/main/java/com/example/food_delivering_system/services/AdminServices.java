package com.example.food_delivering_system.services;


import com.example.food_delivering_system.dto.Response.DishDTO;
import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.dto.Response.RoleDTO;
import com.example.food_delivering_system.dto.Response.UserDTO;

import java.util.List;

public interface AdminServices {

    List<UserDTO> getAllUsers();


    List<UserDTO> getAllCustomers();


    List<RoleDTO> getAllRoles();

    void addRole(RoleDTO roleDto);

    RoleDTO updateRole(Long id, RoleDTO roleDto);

    void deleteRole(Long id);

    List<DishDTO> getAllDishes();

    DishDTO getDishById(Long id);

    DishDTO createDish(DishDTO dishDTO);

    DishDTO updateDish(DishDTO dishDTO);

    void deleteDish(Long id);


    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOngoingOrders();

    List<OrderDTO> getCompletedOrders();

    List<UserDTO> getAllDrivers();

    List<UserDTO>  getAllAvailableDrivers();

    List<UserDTO>  getAllUnavailableDrivers();



}
