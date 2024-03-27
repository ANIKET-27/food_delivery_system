package com.example.food_delivering_system.repository;

import com.example.food_delivering_system.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o WHERE o.orderStatus = 'completed'")
    List<Order> findCompletedOrders();

    @Query("SELECT o FROM Order o WHERE o.orderStatus = 'ongoing'")
    List<Order> findOngoingOrders();




}
