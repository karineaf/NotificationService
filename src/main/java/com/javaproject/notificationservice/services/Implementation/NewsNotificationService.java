package com.javaproject.notificationservice.services.Implementation;


import com.javaproject.notificationservice.gateway.Gateway;
import com.javaproject.notificationservice.services.NotificationService;

public class NewsNotificationService implements NotificationService {
    private Gateway gateway;

    public NewsNotificationService(Gateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void send(String type, String userId, String message) {
        // TASK: IMPLEMENT this
        System.out.println("Sending " + type + " message to user " + userId + ": " + message);
        gateway.send(userId, message);
    }
}