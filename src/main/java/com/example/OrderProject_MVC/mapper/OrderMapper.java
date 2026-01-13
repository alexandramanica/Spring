package com.example.OrderProject_MVC.mapper;

import com.example.OrderProject_MVC.dto.OrderRequestDTO;
import com.example.OrderProject_MVC.dto.OrderResponseDTO;
import com.example.OrderProject_MVC.model.Order;

public class OrderMapper {
    public OrderMapper() {
    }

    public static OrderRequestDTO orderToRequestDTO(Order order){
        return new OrderRequestDTO(order.getOrderDate(),
                order.getPrice(),
                order.getOrderStatus(),
                order.getClient().getEmail());
    }

    public static OrderResponseDTO orderToResponseDTO(Order order){
        return new OrderResponseDTO(order.getId(),
                order.getOrderDate(),
                order.getPrice(),
                order.getOrderStatus(),
                order.getClient().getEmail());
    }
}
