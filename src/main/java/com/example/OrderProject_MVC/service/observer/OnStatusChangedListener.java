package com.example.OrderProject_MVC.service.observer;

import com.example.OrderProject_MVC.model.Notification;
import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.repository.NotificationRepository;
import com.example.OrderProject_MVC.repository.OrderRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OnStatusChangedListener {
    private final OrderRepository orderRepository;
    private final NotificationRepository notificationRepository;

    public OnStatusChangedListener(OrderRepository orderRepository, NotificationRepository notificationRepository) {
        this.orderRepository = orderRepository;
        this.notificationRepository = notificationRepository;
    }

    @EventListener
    public void onOrderStatusChanged(EventOrderChangedStasus event){
        Order order = orderRepository.findById(
                event.getId()).orElse(null);

        if (order == null){
            return;
        }

        String msg = "Comanda nr " +order.getId() + " si-a modificat statusul din " + order.getOrderStatus().toString()
                + " in statusul de " + event.getNewStatus().toString();

        notificationRepository.save(new Notification(msg, LocalDateTime.now(), order));
    }
}
