package com.javaproject.notificationservice.services;


import com.javaproject.notificationservice.model.Notification;

import java.util.List;

public interface NotificationService {
    void sendNotification(Notification notification);

    List<Notification> getNotifications(Long userId);
}