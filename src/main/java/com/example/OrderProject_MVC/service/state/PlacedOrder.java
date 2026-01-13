package com.example.OrderProject_MVC.service.state;

import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.model.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class PlacedOrder implements IOrder {

    @Override
    public OrderStatus sayStatus() {
        System.out.println("Order status" + OrderStatus.PLASATA);
        return OrderStatus.PLASATA;
    }

    @Override
    public void processOrder(Order order) {
        order.setOrderStatus(OrderStatus.PROCESATA);
        System.out.println("The command changed it's state to PROCESSED");
    }

    @Override
    public void shipOrder(Order order) {
        throw new IllegalStateException("The command must be processed first.");
    }

    @Override
    public void deliverOrder(Order order) {
        throw new IllegalStateException("The command must be shipped first.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("The order was cancelled");
    }

}
