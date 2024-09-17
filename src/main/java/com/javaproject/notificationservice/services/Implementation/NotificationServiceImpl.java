package com.javaproject.notificationservice.services.Implementation;

import com.javaproject.notificationservice.services.NotificationService;
import com.javaproject.notificationservice.services.strategy.NotificationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private Map<String, NotificationStrategy> strategies = new HashMap<>();

    @Override
    public void send(String type, Long userId, String message) {

        NotificationStrategy strategy = strategies.get(type.toUpperCase());

        if (strategy == null)
            throw new IllegalArgumentException("No strategy found for notification type: " + type);

        strategy.send(userId, message);
    }
}
