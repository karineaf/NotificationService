package com.javaproject.notificationservice.services.Implementation;


import com.javaproject.notificationservice.entity.NotificationEntity;
import com.javaproject.notificationservice.gateway.Gateway;
import com.javaproject.notificationservice.repository.NotificationRepository;
import com.javaproject.notificationservice.services.NotificationService;
import com.javaproject.notificationservice.utils.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.javaproject.notificationservice.utils.ConstantsUtils.SENT_STATUS_OK;
import static com.javaproject.notificationservice.utils.ConstantsUtils.STATUS_MAX_REQUESTS;
import static com.javaproject.notificationservice.utils.DateUtils.*;

@Service
public class StatusNotificationService implements NotificationService {

    @Autowired
    private Gateway gateway;

    @Autowired
    private NotificationRepository repository;

    @Override
    public void send(String type, Long userId, String message) {

        Date nowDate = new Date();
        Date nowMinusOneMinute = getDateWithMinuteMinusOne(nowDate);

        List<NotificationEntity> notifications = repository.findAllByUserId(userId);
        if (notifications.isEmpty()) {
            String message_delivered_status = gateway.send(userId, message);

            if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                repository.save(new NotificationEntity(userId, NotificationType.fromString(type), new Date()));
        } else {
            List<NotificationEntity> lastPeriodNotifications = notifications.stream()
                    .filter(n -> n.getSentDate().after(nowMinusOneMinute)).toList();
            if (lastPeriodNotifications.isEmpty()){
                String message_delivered_status = gateway.send(userId, message);

                if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                    repository.save(new NotificationEntity(userId, NotificationType.fromString(type), nowDate));
            }
            else {
                if(lastPeriodNotifications.size() < STATUS_MAX_REQUESTS){
                    String message_delivered_status = gateway.send(userId, message);

                    if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                        repository.save(new NotificationEntity(userId, NotificationType.fromString(type), nowDate));
                } else {
                    System.out.println("Notification of type Status was already sent in this day.");
                }
            }
        }
    }
}