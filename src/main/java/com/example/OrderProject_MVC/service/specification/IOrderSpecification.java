package com.example.OrderProject_MVC.service.specification;

import com.example.OrderProject_MVC.model.Order;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IOrderSpecification {
    Specification<Order> getOrdersSpecification(String filterSpecification);

    FilterSpecification getFilterSpecification();
}
