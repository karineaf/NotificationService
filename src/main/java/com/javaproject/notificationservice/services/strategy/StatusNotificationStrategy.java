package com.javaproject.notificationservice.services.strategy;

import com.javaproject.notificationservice.entity.NotificationEntity;
import com.javaproject.notificationservice.entity.NotificationKeyEntity;
import com.javaproject.notificationservice.gateway.Gateway;
import com.javaproject.notificationservice.repository.NotificationRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.javaproject.notificationservice.utils.ConstantsUtils.SENT_STATUS_OK;
import static com.javaproject.notificationservice.utils.ConstantsUtils.STATUS_MAX_REQUESTS;
import static com.javaproject.notificationservice.utils.DateUtils.getDateWithMinuteMinusOne;
import static com.javaproject.notificationservice.utils.NotificationType.STATUS;
import static org.slf4j.LoggerFactory.getLogger;



@Component("STATUS")
public class StatusNotificationStrategy implements NotificationStrategy {
    @Autowired
    private Gateway gateway;

    @Autowired
    private NotificationRepository repository;

    private static final Logger log = getLogger(StatusNotificationStrategy.class);


    @Override
    public void send(Long userId, String message) {

        Date nowDate = new Date();
        Date nowMinusOneMinute = getDateWithMinuteMinusOne(nowDate);

        List<NotificationEntity> notifications = repository.findAllByIdUserIdAndType(userId, STATUS.name());
        if (notifications.isEmpty()) {
            String message_delivered_status = gateway.send(userId, message);

            if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), STATUS.name()));
        } else {
            List<NotificationEntity> lastPeriodNotifications = notifications.stream()
                    .filter(n -> n.getId().getSentDate().after(nowMinusOneMinute)).toList();
            if (lastPeriodNotifications.isEmpty()){
                String message_delivered_status = gateway.send(userId, message);

                if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                    repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), STATUS.name()));
            }
            else {
                if(lastPeriodNotifications.size() < STATUS_MAX_REQUESTS){
                    String message_delivered_status = gateway.send(userId, message);

                    if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                        repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), STATUS.name()));
                } else {
                    log.info("Status notifications have already been sent to this customer in the last minute.");
                }
            }
        }
    }
}