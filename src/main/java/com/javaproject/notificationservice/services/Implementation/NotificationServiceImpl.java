package com.javaproject.notificationservice.services.Implementation;

import com.javaproject.notificationservice.services.NotificationService;
import com.javaproject.notificationservice.services.strategy.NotificationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final Map<String, NotificationStrategy> strategies;

    @Autowired
    public NotificationServiceImpl(Map<String, NotificationStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public void send(String type, Long userId, String message) {
        try {
            NotificationStrategy strategy = strategies.get(type.toUpperCase());

            if (strategy == null)
                throw new IllegalArgumentException("No strategy found for notification type: " + type);

            strategy.send(userId, message);
        } catch (IllegalArgumentException ex){
            System.out.printf("Could not send the message to customer %s. Error: %s%n", userId, ex);
        }
    }
}
