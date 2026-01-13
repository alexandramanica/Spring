package com.example.OrderProject_MVC.mapper;

import com.example.OrderProject_MVC.dto.NotificationResponseDTO;
import com.example.OrderProject_MVC.model.Notification;

public class NotificationMapper {
    public static NotificationResponseDTO notificationToDto(Notification notification){
        return new NotificationResponseDTO(notification.getId(),
                notification.getNotificationMessage(),
                notification.getCreatedAt());
    }
}
