package com.example.OrderProject_MVC.service;

import com.example.OrderProject_MVC.dto.NotificationResponseDTO;
import com.example.OrderProject_MVC.mapper.NotificationMapper;
import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.repository.NotificationRepository;
import com.example.OrderProject_MVC.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final OrderRepository orderRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(OrderRepository orderRepository, NotificationRepository notificationRepository) {
        this.orderRepository = orderRepository;
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationResponseDTO> findNotifications(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        List<NotificationResponseDTO> notificationResponseDTOS = notificationRepository.findByOrder(order).stream().map(NotificationMapper::notificationToDto).toList();
        return notificationResponseDTOS;
    }
}
