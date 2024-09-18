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
import static com.javaproject.notificationservice.utils.DateUtils.getDayOfYear;
import static com.javaproject.notificationservice.utils.NotificationType.NEWS;
import static org.slf4j.LoggerFactory.getLogger;


@Component("NEWS")
public class NewsNotificationStrategy implements NotificationStrategy {

    @Autowired
    private Gateway gateway;

    @Autowired
    private NotificationRepository repository;

    private static final Logger log = getLogger(NewsNotificationStrategy.class);

    @Override
    public void send(Long userId, String message) {

        Date nowDate = new Date();
        int todayNumber = getDayOfYear(nowDate);

        List<NotificationEntity> notifications = repository.findAllByIdUserIdAndType(userId, NEWS.name());
        if (notifications.isEmpty()) {
            String message_delivered_status = gateway.send(userId, message);

            if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), NEWS.name()));
        }
        else {
            List<NotificationEntity> lastPeriodNotifications = notifications.stream()
                    .filter(n -> todayNumber == getDayOfYear(n.getId().getSentDate())).toList();
            if (lastPeriodNotifications.isEmpty()){
                String message_delivered_status = gateway.send(userId, message);

                if (Objects.equals(message_delivered_status, SENT_STATUS_OK))
                    repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), NEWS.name()));
            }
            else {
                log.info("News notifications have already been sent to this customer today.");
            }
        }
    }
}