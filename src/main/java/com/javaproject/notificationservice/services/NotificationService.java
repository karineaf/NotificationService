package com.javaproject.notificationservice.services;


public interface NotificationService {
    void send(String type, Long userId, String message);
}