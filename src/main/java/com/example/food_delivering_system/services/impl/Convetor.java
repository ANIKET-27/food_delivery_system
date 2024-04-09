package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.DTO.DishDTO;
import com.example.food_delivering_system.DTO.DriverDTO;
import com.example.food_delivering_system.DTO.OrderDTO;
import com.example.food_delivering_system.DTO.UserDTO;
import com.example.food_delivering_system.entities.Dish;
import com.example.food_delivering_system.entities.Driver;
import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.BreakIterator;


public class Convetor {


    static ModelMapper modelMapper = new ModelMapper();

    public static User userDtoToUser(UserDTO userDTO) { return modelMapper.map(userDTO,User.class);}

    public static  UserDTO userToUserDto(User user){
  return modelMapper.map(user, UserDTO.class);

//        return  UserDTO.builder()
//                .user_id(user.getUser_id())
//                .userName(user.getUserName())
//                .password(user.getPassword())
//                .email(user.getEmail())
//                .latitude(user.getLatitude())
//                .longitude(user.getLongitude())
//                .phoneNo(user.getPhoneNo())
//                .cart(user.getCart())
//                .order(user.getOrder())
//                .build();
    }

  public   static Order orderDtoToOrder(OrderDTO orderDTO){
       return modelMapper.map(orderDTO,Order.class);
    }

   public static OrderDTO orderToOrderDto(Order order){
       return modelMapper.map(order, OrderDTO.class);
    }

    public static Driver driverDtotoDriver(DriverDTO driverDTO){
       return modelMapper.map(driverDTO, Driver.class);
    }

   public    static DriverDTO driverToDriverDto(Driver driver){
       return  modelMapper.map(driver, DriverDTO.class);
    }

    public static Dish dishDtoToDish(DishDTO dishDTO) {

       return  modelMapper.map(dishDTO,Dish.class);

    }
    public static DishDTO dishToDishDto (Dish dish) {

      return  modelMapper.map(dish,DishDTO.class);


    }


}
