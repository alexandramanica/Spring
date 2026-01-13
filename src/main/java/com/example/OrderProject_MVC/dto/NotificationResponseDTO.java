package com.example.OrderProject_MVC.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class NotificationResponseDTO {
    private Long id;
    private String notificationMessage;
    private LocalDateTime createdAt;

    public NotificationResponseDTO(Long id, String notificationMessage, LocalDateTime createdAt) {
        this.id = id;
        this.notificationMessage = notificationMessage;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
