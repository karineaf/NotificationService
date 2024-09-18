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

import static com.javaproject.notificationservice.utils.ConstantsUtils.MARKETING_MAX_REQUESTS;
import static com.javaproject.notificationservice.utils.ConstantsUtils.SENT_STATUS_OK;
import static com.javaproject.notificationservice.utils.DateUtils.getDateWithHourMinusThree;
import static com.javaproject.notificationservice.utils.NotificationType.MARKETING;
import static org.slf4j.LoggerFactory.getLogger;

@Component("MARKETING")
public class MarketingNotificationStrategy implements NotificationStrategy{

    @Autowired
    private Gateway gateway;

    @Autowired
    private NotificationRepository repository;

    private static final Logger log = getLogger(MarketingNotificationStrategy.class);

    @Override
    public void send(Long userId, String message) {

        Date nowDate = new Date();
        Date nowMinusThreeHours = getDateWithHourMinusThree(nowDate);
        List<NotificationEntity> notifications = repository.findAllByIdUserIdAndType(userId, MARKETING.name());
        if (notifications.isEmpty()) {
            String message_delivered_status = gateway.send(userId, message);

            if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), MARKETING.name()));
        } else {
            List<NotificationEntity> lastPeriodNotifications = notifications.stream()
                    .filter(n -> n.getId().getSentDate().after(nowMinusThreeHours)).toList();
            if (lastPeriodNotifications.isEmpty()){
                String message_delivered_status = gateway.send(userId, message);

                if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                    repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), MARKETING.name()));
            }
            else {
                if (lastPeriodNotifications.size() < MARKETING_MAX_REQUESTS) {
                    String message_delivered_status = gateway.send(userId, message);

                    if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                        repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), MARKETING.name()));
                } else {
                    log.info("Marketing notifications have already been sent in the last three hours");
                }
            }
        }
    }
}
