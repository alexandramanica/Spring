package com.example.OrderProject_MVC.service.state;

import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.model.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class ProccesedOrder implements IOrder{

    @Override
    public OrderStatus sayStatus() {
        System.out.println("Order status" + OrderStatus.PROCESATA);
        return OrderStatus.PROCESATA;
    }

    @Override
    public void processOrder(Order order) {
        throw new IllegalStateException("The command was already processed.");
    }

    @Override
    public void shipOrder(Order order) {
        order.setOrderStatus(OrderStatus.EXPEDIATA);
        System.out.println("The command changed it's state to SHIPPED");
    }

    @Override
    public void deliverOrder(Order order) {
        throw new IllegalStateException("The command must be shipped first.");
    }

    @Override
    public void cancelOrder(Order order) {
        throw new IllegalStateException("Only placed orders can be canceled.");
    }
}
