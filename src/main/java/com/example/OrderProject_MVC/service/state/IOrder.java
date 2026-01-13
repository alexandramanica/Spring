package com.example.OrderProject_MVC.service.state;

import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.model.OrderStatus;

public interface IOrder {
    OrderStatus sayStatus();
    void processOrder(Order order) ;
    void shipOrder(Order order);
    void deliverOrder(Order order);
    void cancelOrder(Order order);
}
