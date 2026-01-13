package com.example.OrderProject_MVC.service.observer;

import com.example.OrderProject_MVC.model.OrderStatus;

public class EventOrderChangedStasus {
    private final Long id;
    private final OrderStatus oldStatus;
    private final OrderStatus newStatus;

    public EventOrderChangedStasus(Long id, OrderStatus oldStatus, OrderStatus newStatus) {
        this.id = id;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    public Long getId() {
        return id;
    }

    public OrderStatus getOldStatus() {
        return oldStatus;
    }

    public OrderStatus getNewStatus() {
        return newStatus;
    }

}
