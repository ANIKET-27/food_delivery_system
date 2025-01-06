package com.example.food_delivering_system.repository;

import com.example.food_delivering_system.entities.Order;
import com.example.food_delivering_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o WHERE o.orderStatus = 3 OR o.orderStatus= -1")
    List<Order> findCompletedOrders();

    @Query("SELECT o FROM Order o WHERE o.orderStatus <> 3")
    List<Order> findOngoingOrders();

    @Query("SELECT o FROM Order o WHERE o.user = :user AND  (o.orderStatus = 4 OR o.orderStatus = -1)")
    List<Order> findCompletedOrdersByUser(User user);

    @Query("SELECT o FROM Order o WHERE o.user = :user AND o.orderStatus BETWEEN 0 AND 3")
    List<Order> findOngoingOrdersByUser(User user);

    @Query("SELECT o FROM Order o WHERE o.driver = :driver AND  (o.orderStatus = 4 OR o.orderStatus = -1)")
    List<Order> findCompletedOrdersByDriver(User driver);

    @Query("SELECT o FROM Order o WHERE o.driver = :driver AND (o.orderStatus <> 4 OR  o.orderStatus <> -1)")
    List<Order> findOngoingOrdersByDriver(User driver);

    @Query("SELECT o FROM Order o WHERE o.driver IS NULL")
    List<Order> findAvailableOrder();

}
