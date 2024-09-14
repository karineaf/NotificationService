package com.javaproject.notificationservice.services.Implementation;


import com.javaproject.notificationservice.gateway.Gateway;
import com.javaproject.notificationservice.repository.NotificationRepository;
import com.javaproject.notificationservice.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;

public class MarketingNotificationService implements NotificationService {

    @Autowired
    private Gateway gateway;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void send(String type, String userId, String message) {
        // TASK: IMPLEMENT this

        gateway.send(message, userId);
    }
}