package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.dto.Response.DishDTO;
import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.dto.Response.RoleDTO;
import com.example.food_delivering_system.dto.Response.UserDTO;
import com.example.food_delivering_system.entities.Dish;
import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.Role;
import com.example.food_delivering_system.entities.User;
import com.example.food_delivering_system.repository.DishRepository;
import com.example.food_delivering_system.repository.OrderRepository;
import com.example.food_delivering_system.repository.RoleRepository;
import com.example.food_delivering_system.repository.UserRepository;
import com.example.food_delivering_system.services.AdminServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminServicesImpl implements AdminServices {

    private final DishRepository dishRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final RoleRepository roleRepository;

    public AdminServicesImpl(DishRepository dishRepository,UserRepository userRepository, OrderRepository orderRepository, RoleRepository roleRepository){
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.roleRepository = roleRepository;

    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> list = new ArrayList<>();
        for(User u : userRepository.findAll()) list.add(Convetor.userToUserDto(u));

        return list;

    }



    @Override
    public List<UserDTO> getAllCustomers() {
        List<UserDTO> customers = new ArrayList<>();

        List<User> users = userRepository.findAllCustomers();

        for(User u : users) customers.add(Convetor.userToUserDto(u));

        return customers;
    }



    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        List<RoleDTO> roleDTOs = new ArrayList<>();

        for(Role r : roleList ) roleDTOs.add(Convetor.roleToRoleDTO(r));

        return roleDTOs;

    }

    @Override
    public void addRole(RoleDTO roleDto) {

    }

    @Override
    public RoleDTO updateRole(Long id,RoleDTO roleDto) {
        return null;
    }

    @Override
    public void deleteRole(Long id) {

    }

    @Override
    public List<DishDTO> getAllDishes() {
        List<DishDTO> dishDTOList = new ArrayList<>();

        List<Dish>  dishList = dishRepository.findAll();

        for(Dish d : dishList) dishDTOList.add(Convetor.dishToDishDto(d));

        return dishDTOList;

    }

    @Override
    public DishDTO getDishById(Long id) {
        Optional<Dish> optDish = dishRepository.findById(id);

        if(optDish.isEmpty()) throw  new RuntimeException("Dish Not Available");

        return Convetor.dishToDishDto(optDish.get());

    }

    @Override
    public DishDTO createDish(DishDTO dishDTO) {
        Dish d = Dish.builder()
                .name(dishDTO.getName())
                .description(dishDTO.getDescription())
                .price(dishDTO.getPrice())
                .url(dishDTO.getUrl())
                .category(dishDTO.getCategory())
                .build();


        Dish saved = dishRepository.save(d);

        return  Convetor.dishToDishDto(saved);

    }

    @Override
    public DishDTO updateDish(DishDTO dishDTO) {
        Dish d = Convetor.dishDtoToDish(dishDTO);
        d = dishRepository.save(d);

        return Convetor.dishToDishDto(d);
    }

    @Override
    public void deleteDish(Long id) {

        Optional<Dish>  optDish = dishRepository.findById(id);

        if(optDish.isEmpty()) throw  new RuntimeException("Dish Not Available To Delete");

        dishRepository.deleteById(id);

    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> ordersDto = new ArrayList<>();

        List<Order> allOrders = orderRepository.findAll();

        for(Order order : allOrders) ordersDto.add(Convetor.orderToOrderDto(order));

        return ordersDto;

    }

    @Override
    public List<OrderDTO> getOngoingOrders() {
        List<OrderDTO> ordersDto = new ArrayList<>();

        List<Order> allOrders = orderRepository.findOngoingOrders();

        for(Order order : allOrders) ordersDto.add(Convetor.orderToOrderDto(order));

        return ordersDto;
    }

    @Override
    public List<OrderDTO> getCompletedOrders() {

        List<OrderDTO> ordersDto = new ArrayList<>();

        List<Order> allOrders = orderRepository.findCompletedOrders();

        for(Order order : allOrders) ordersDto.add(Convetor.orderToOrderDto(order));

        return ordersDto;
    }

    @Override
    public List<UserDTO> getAllDrivers() {
        List<User> driverList =  userRepository.findAll();
        List<UserDTO> driverDtoList = new ArrayList<>();

        for(User d : driverList) driverDtoList.add(Convetor.userToUserDto(d));

        return driverDtoList;
    }

    @Override
    public List<UserDTO> getAllUnavailableDrivers() {
        List<User> driverList = userRepository.findAllUnavailableDriver();

        List<UserDTO> driverDTOList = new ArrayList<>();

        for(User d : driverList) driverDTOList.add(Convetor.userToUserDto(d));

        return driverDTOList;
    }

    @Override
    public List<UserDTO> getAllAvailableDrivers() {

        List<User> driverList = userRepository.findAllAvailableDriver();

        List<UserDTO> driverDTOList = new ArrayList<>();

        for(User d : driverList) driverDTOList.add(Convetor.userToUserDto(d));

        return driverDTOList;

    }



}
