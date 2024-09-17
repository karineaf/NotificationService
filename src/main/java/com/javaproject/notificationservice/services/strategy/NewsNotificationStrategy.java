package com.javaproject.notificationservice.services.strategy;

import com.javaproject.notificationservice.entity.NotificationEntity;
import com.javaproject.notificationservice.entity.NotificationKeyEntity;
import com.javaproject.notificationservice.gateway.Gateway;
import com.javaproject.notificationservice.repository.NotificationRepository;
import com.javaproject.notificationservice.utils.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.javaproject.notificationservice.utils.ConstantsUtils.SENT_STATUS_OK;
import static com.javaproject.notificationservice.utils.DateUtils.getDayOfYear;
import static com.javaproject.notificationservice.utils.NotificationType.NEWS;

@Service("news")
public class NewsNotificationStrategy implements NotificationStrategy {

    @Autowired
    private Gateway gateway;

    @Autowired
    private NotificationRepository repository;

    @Override
    public void send(Long userId, String message) {

        Date nowDate = new Date();
        int todayNumber = getDayOfYear(nowDate);

        List<NotificationEntity> notifications = repository.findAllByIdUserId(userId);
        if (notifications.isEmpty()) {
            String message_delivered_status = gateway.send(userId, message);

            if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), NEWS));
        }
        else {
            List<NotificationEntity> lastPeriodNotifications = notifications.stream()
                    .filter(n -> todayNumber == getDayOfYear(n.getId().getSentDate())).toList();
            if (lastPeriodNotifications.isEmpty()){
                String message_delivered_status = gateway.send(userId, message);

                if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                    repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), NEWS));
            }
            else {
                System.out.println("Notification of type News was already sent in this day.");
            }
        }
    }
}