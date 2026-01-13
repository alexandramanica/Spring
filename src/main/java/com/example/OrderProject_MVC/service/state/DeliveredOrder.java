package com.example.OrderProject_MVC.service.state;

import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.model.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class DeliveredOrder implements IOrder{
    @Override
    public OrderStatus sayStatus() {
        System.out.println("Order status" + OrderStatus.LIVRATA);
        return OrderStatus.LIVRATA;
    }

    @Override
    public void processOrder(Order order) {
        throw new IllegalStateException("The command was already delivered.");
    }

    @Override
    public void shipOrder(Order order) {
        throw new IllegalStateException("The command was already delivered.");
    }

    @Override
    public void deliverOrder(Order order) {
        throw new IllegalStateException("The command was already delivered.");
    }

    @Override
    public void cancelOrder(Order order) {
        throw new IllegalStateException("Only placed orders can be canceled.");
    }


}
