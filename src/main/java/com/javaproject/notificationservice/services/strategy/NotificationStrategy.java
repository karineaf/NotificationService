package com.javaproject.notificationservice.services.strategy;

public interface NotificationStrategy {
    void send(Long userId, String message);

    void save(String message_delivered_status, Long userId);
}