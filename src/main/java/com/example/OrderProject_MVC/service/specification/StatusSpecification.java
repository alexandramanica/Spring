package com.example.OrderProject_MVC.service.specification;

import com.example.OrderProject_MVC.model.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusSpecification implements IOrderSpecification{
    @Override
    public Specification<Order> getOrdersSpecification(String filterSpecification) {
        return (root, query, cb) -> cb.equal(root.get("orderStatus"), filterSpecification);
    }

    @Override
    public FilterSpecification getFilterSpecification() {
        return FilterSpecification.STATUS;
    }
}
