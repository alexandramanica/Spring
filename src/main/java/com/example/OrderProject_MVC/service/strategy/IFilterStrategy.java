package com.example.OrderProject_MVC.service.strategy;

import com.example.OrderProject_MVC.model.Order;

import java.util.List;

public interface IFilterStrategy {
    List<Order> filterOrders(String filterValue);
    Filter getFilter();
}
