package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.dto.Response.DishDTO;
import com.example.food_delivering_system.dto.Response.OrderDTO;
import com.example.food_delivering_system.dto.Response.RoleDTO;
import com.example.food_delivering_system.dto.Response.UserDTO;
import com.example.food_delivering_system.entities.Dish;
import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.Role;
import com.example.food_delivering_system.entities.User;
import org.modelmapper.ModelMapper;


public class Convetor {


    static ModelMapper modelMapper = new ModelMapper();

    public static User userDtoToUser(UserDTO userDTO) { return modelMapper.map(userDTO, User.class);}

    public static  UserDTO userToUserDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }


    public   static Order orderDtoToOrder(OrderDTO orderDTO){
       return modelMapper.map(orderDTO,Order.class);
    }

    public static OrderDTO orderToOrderDto(Order order){
       OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

       orderDTO.setCustomerName(order.getUser().getUserName());
       orderDTO.setUserId(order.getUser().getUserId());
       orderDTO.setCustomerPhNo(order.getUser().getPhoneNo());

       if(order.getDriver() != null) {

           orderDTO.setCustomerName(order.getDriver().getUserName());
           orderDTO.setDriverName(order.getDriver().getUserName());
           orderDTO.setDriverPhNo(order.getDriver().getPhoneNo());

       }


       return orderDTO;
    }


    public static Dish dishDtoToDish(DishDTO dishDTO) {

       return  modelMapper.map(dishDTO,Dish.class);

    }
    public static DishDTO dishToDishDto (Dish dish) {

      return  modelMapper.map(dish,DishDTO.class);


    }

    public static Role roleDtoToRole(RoleDTO roleDto){

        return modelMapper.map(roleDto, Role.class);

    }

    public static RoleDTO roleToRoleDTO(Role role){

        return modelMapper.map(role, RoleDTO.class);

    }

    public static User reqToUser(CreateUserDTO userDTO) {

        return  modelMapper.map(userDTO, User.class);

    }



}
