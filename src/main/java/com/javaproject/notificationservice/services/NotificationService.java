package com.javaproject.notificationservice.services;


public interface NotificationService {
    void send(String type, String userId, String message);
}