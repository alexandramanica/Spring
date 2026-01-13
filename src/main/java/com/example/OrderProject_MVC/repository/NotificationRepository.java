package com.example.OrderProject_MVC.repository;

import com.example.OrderProject_MVC.model.Client;
import com.example.OrderProject_MVC.model.Notification;
import com.example.OrderProject_MVC.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

//pt specification JpaSpecificationExecutor<Order>
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByOrder(Order order);

}
