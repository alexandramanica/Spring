package com.example.OrderProject_MVC.repository;

import com.example.OrderProject_MVC.model.Client;
import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    List<Order> findByOrderStatus(OrderStatus orderStatus);
    List<Order> findByOrderDate(LocalDate orderDate);
    List<Order> findByClient(Client client);
}
