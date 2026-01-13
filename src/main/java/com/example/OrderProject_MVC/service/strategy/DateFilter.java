package com.example.OrderProject_MVC.service.strategy;

import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DateFilter implements IFilterStrategy {
    private final OrderRepository orderRepository;

    public DateFilter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public List<Order> filterOrders(String filterValue) {
        return orderRepository.findByOrderDate(LocalDate.parse(filterValue));
    }

    @Override
    public Filter getFilter() {
        return Filter.DATE;
    }
}
