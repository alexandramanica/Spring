package com.example.OrderProject_MVC.service.strategy;

import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.model.OrderStatus;
import com.example.OrderProject_MVC.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusFilter implements IFilterStrategy {
    private final OrderRepository orderRepository;

    public StatusFilter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> filterOrders(String filterValue) {
        List<Order> orderList = orderRepository.findByOrderStatus(OrderStatus.valueOf(filterValue));
        return orderList;
    }

    @Override
    public Filter getFilter() {
        return Filter.STATUS;
    }
}
