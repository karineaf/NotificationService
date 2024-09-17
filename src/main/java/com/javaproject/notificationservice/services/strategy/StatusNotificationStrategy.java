package com.javaproject.notificationservice.services.strategy;

import com.javaproject.notificationservice.entity.NotificationEntity;
import com.javaproject.notificationservice.entity.NotificationKeyEntity;
import com.javaproject.notificationservice.gateway.Gateway;
import com.javaproject.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.javaproject.notificationservice.utils.ConstantsUtils.SENT_STATUS_OK;
import static com.javaproject.notificationservice.utils.ConstantsUtils.STATUS_MAX_REQUESTS;
import static com.javaproject.notificationservice.utils.DateUtils.getDateWithMinuteMinusOne;
import static com.javaproject.notificationservice.utils.NotificationType.STATUS;

@Service("status")
public class StatusNotificationStrategy implements NotificationStrategy {
    @Autowired
    private Gateway gateway;

    @Autowired
    private NotificationRepository repository;

    @Override
    public void send(Long userId, String message) {

        Date nowDate = new Date();
        Date nowMinusOneMinute = getDateWithMinuteMinusOne(nowDate);

        List<NotificationEntity> notifications = repository.findAllByIdUserId(userId);
        if (notifications.isEmpty()) {
            String message_delivered_status = gateway.send(userId, message);

            if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), STATUS));
        } else {
            List<NotificationEntity> lastPeriodNotifications = notifications.stream()
                    .filter(n -> n.getId().getSentDate().after(nowMinusOneMinute)).toList();
            if (lastPeriodNotifications.isEmpty()){
                String message_delivered_status = gateway.send(userId, message);

                if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                    repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), STATUS));
            }
            else {
                if(lastPeriodNotifications.size() < STATUS_MAX_REQUESTS){
                    String message_delivered_status = gateway.send(userId, message);

                    if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                        repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), STATUS));
                } else {
                    System.out.println("Notification of type Status was already sent in this day.");
                }
            }
        }
    }
}