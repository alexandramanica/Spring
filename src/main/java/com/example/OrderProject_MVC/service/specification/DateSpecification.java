package com.example.OrderProject_MVC.service.specification;

import com.example.OrderProject_MVC.model.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DateSpecification implements IOrderSpecification{
    @Override
    public Specification<Order> getOrdersSpecification(String filterSpecification) {
        if (filterSpecification == null || filterSpecification.isBlank()) {
            throw new IllegalArgumentException("Date filter cannot be empty.");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(filterSpecification.trim()); // yyyy-MM-dd
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd.");
        }

        return (root, query, cb) -> cb.equal(root.get("orderDate"), date);
    }

    @Override
    public FilterSpecification getFilterSpecification() {
        return FilterSpecification.DATE;
    }
}
