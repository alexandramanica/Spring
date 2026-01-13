package com.example.OrderProject_MVC.dto;

import com.example.OrderProject_MVC.model.OrderStatus;

import java.time.LocalDate;

public class OrderResponseDTO {
    private Long id;
    private LocalDate orderDate;
    private Double price;
    private OrderStatus orderStatus;
    private String email;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Long id, LocalDate orderDate, Double price, OrderStatus orderStatus, String email) {
        this.id = id;
        this.orderDate = orderDate;
        this.price = price;
        this.orderStatus = orderStatus;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
