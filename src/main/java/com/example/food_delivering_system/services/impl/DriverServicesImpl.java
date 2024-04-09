package com.example.food_delivering_system.services.impl;

import com.example.food_delivering_system.DTO.DriverDTO;
import com.example.food_delivering_system.DTO.OrderDTO;
import com.example.food_delivering_system.entities.Driver;
import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.repository.DriverRepository;
import com.example.food_delivering_system.repository.OrderRepository;
import com.example.food_delivering_system.services.DriverServices;
import jakarta.persistence.Converter;
import org.hibernate.query.sqm.tree.expression.Conversion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServicesImpl implements DriverServices {

    private final DriverRepository driverRepository;
    private final OrderRepository orderRepository;

    public  DriverServicesImpl(DriverRepository driverRepository, OrderRepository orderRepository){
        this.driverRepository = driverRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        List<Driver> driverList =  driverRepository.findAll();
        List<DriverDTO> driverDtoList = new ArrayList<>();

        for(Driver d : driverList) driverDtoList.add(Convetor.driverToDriverDto(d));

        return driverDtoList;
    }

    @Override
    public List<DriverDTO> getAllAvailableDrivers() {

        List<Driver> driverList = driverRepository.findAllAvailableDriver();

        List<DriverDTO> driverDTOList = new ArrayList<>();

        for(Driver d : driverList) driverDTOList.add(Convetor.driverToDriverDto(d));

        return driverDTOList;

    }

    @Override
    public DriverDTO createDriver(DriverDTO driverDTO) {
        Driver driver = Convetor.driverDtotoDriver(driverDTO);
        driver  =  driverRepository.save(driver);

        return Convetor.driverToDriverDto(driver);

    }

    @Override
    public DriverDTO updateDriver(DriverDTO driverDTO) {

        Driver driver = Convetor.driverDtotoDriver(driverDTO);
        driver  =  driverRepository.save(driver);

        return Convetor.driverToDriverDto(driver);
    }

    @Override
    public DriverDTO getDriverById(Long id) {
        Optional<Driver> driverOpt = driverRepository.findById(id);

        if(driverOpt.isEmpty()) throw new RuntimeException("Cannot Find The Driver");

        return Convetor.driverToDriverDto(driverOpt.get());
    }

    @Override
    public List<OrderDTO> getPastOrder(Long id){
        DriverDTO driverDTO = getDriverById(id);


        List<Order> orders = driverDTO.getOrders();

        List<OrderDTO> orderDto = new ArrayList<>();

        for(Order o : orders)
            if(o.getOrderStatus().equals("Completed")) {
                orderDto.add(Convetor.orderToOrderDto(o));
            }

        return orderDto;


    }

    @Override
    public List<OrderDTO> getCurrentOrder(Long id) {
        DriverDTO driverDTO = getDriverById(id);


        List<Order> orders = driverDTO.getOrders();

        List<OrderDTO> orderDto = new ArrayList<>();

        // Also add the different stages that could be in the orders

        for(Order o : orders)
            if(!o.getOrderStatus().equals("Completed")) {
                orderDto.add(Convetor.orderToOrderDto(o));
            }

        return orderDto;

    }

    @Override
    public void assignDriverToOrder(Long orderId, Long driverId) {

        Optional<Driver> driver = driverRepository.findById(driverId);
        Optional<Order> order = orderRepository.findById(orderId);

        if(driver.isEmpty() || order.isEmpty()) throw  new RuntimeException("Something went wrong with the assinging driver");

        if(driver.get().getAvailable()) {


            order.get().setDriver(driver.get());
            driver.get().getOrders().add(order.get());


            // Make the function to change the availability of the driver


            orderRepository.save(order.get());
            driverRepository.save(driver.get());


        }
        else
            throw  new RuntimeException("DRIVER IS NO AVAILABLE");


    }

    @Override
    public void updateStatusForDelivery(Long orderId, String str) {

        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isEmpty()) throw  new RuntimeException("Something went wrong with the assinging driver");

        order.get().setOrderStatus(str);

        if(str.equals("Completed")){

            // Set Driver Availabel

        }

        orderRepository.save(order.get());



    }


}
