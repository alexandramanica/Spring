package com.example.OrderProject_MVC.service.state;

import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.model.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class ShippedOrder implements IOrder{

    @Override
    public OrderStatus sayStatus() {
        System.out.println("Order status" + OrderStatus.EXPEDIATA);
        return OrderStatus.EXPEDIATA;
    }

    @Override
    public void processOrder(Order order) {
        throw new IllegalStateException("The command was already shipped.");
    }

    @Override
    public void shipOrder(Order order) {
        throw new IllegalStateException("The command was already shipped.");
    }

    @Override
    public void deliverOrder(Order order) {
        order.setOrderStatus(OrderStatus.LIVRATA);
        System.out.println("The command changed it's state to DELIVERED");
    }
    @Override
    public void cancelOrder(Order order) {
        throw new IllegalStateException("Only placed orders can be canceled.");
    }

}
