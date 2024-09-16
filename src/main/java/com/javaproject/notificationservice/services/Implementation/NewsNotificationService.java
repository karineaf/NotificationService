package com.javaproject.notificationservice.services.Implementation;


import com.javaproject.notificationservice.entity.NotificationEntity;
import com.javaproject.notificationservice.gateway.Gateway;
import com.javaproject.notificationservice.repository.NotificationRepository;
import com.javaproject.notificationservice.services.NotificationService;
import com.javaproject.notificationservice.utils.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.javaproject.notificationservice.utils.ConstantsUtils.SENT_STATUS_OK;
import static com.javaproject.notificationservice.utils.DateUtils.getDayOfYear;

@Service
public class NewsNotificationService implements NotificationService {

    @Autowired
    private Gateway gateway;

    @Autowired
    private NotificationRepository repository;

    @Override
    public void send(String type, Long userId, String message) {

        Date nowDate = new Date();
        int todayNumber = getDayOfYear(nowDate);

        List<NotificationEntity> notifications = repository.findAllByUserId(userId);
        if (notifications.isEmpty()) {
            String message_delivered_status = gateway.send(userId, message);

            if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                repository.save(new NotificationEntity(userId, NotificationType.fromString(type), nowDate));
        }
        else {
            List<NotificationEntity> lastPeriodNotifications = notifications.stream()
                    .filter(n -> todayNumber == getDayOfYear(n.getSentDate())).toList();
            if (lastPeriodNotifications.isEmpty()){
                String message_delivered_status = gateway.send(userId, message);

                if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                    repository.save(new NotificationEntity(userId, NotificationType.fromString(type), nowDate));
            }
            else {
                System.out.println("Notification of type News was already sent in this day.");
            }
        }
    }
}