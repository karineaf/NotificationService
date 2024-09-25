package com.javaproject.notificationservice.services.strategy;

import com.javaproject.notificationservice.entity.NotificationEntity;
import com.javaproject.notificationservice.entity.NotificationKeyEntity;
import com.javaproject.notificationservice.gateway.Gateway;
import com.javaproject.notificationservice.repository.NotificationRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.javaproject.notificationservice.utils.ConstantsUtils.SENT_STATUS_OK;
import static com.javaproject.notificationservice.utils.DateUtils.getDateMinusMinutes;
import static com.javaproject.notificationservice.utils.NotificationType.STATUS;
import static java.lang.Integer.parseInt;
import static org.slf4j.LoggerFactory.getLogger;



@Component("STATUS")
public class StatusNotificationStrategy implements NotificationStrategy {

    @Value("${status.max.requests}")
    private static String STATUS_MAX_REQUESTS;

    @Value("${status.period.requests}")
    private static String STATUS_PERIOD_REQUESTS;

    @Autowired
    private Gateway gateway;

    @Autowired
    private NotificationRepository repository;

    private static final Logger log = getLogger(StatusNotificationStrategy.class);


    @Override
    public void send(Long userId, String message) {

        Date nowMinusMinutes = getDateMinusMinutes(new Date(), parseInt(STATUS_PERIOD_REQUESTS));

        List<NotificationEntity> notifications = repository.findAllByIdUserIdAndType(userId, STATUS.name());
        if (notifications.isEmpty()) {
            String messageDeliveredStatus = gateway.send(userId, message);
            save(messageDeliveredStatus, userId);
        } else {

            List<NotificationEntity> lastPeriodNotifications = notifications.stream()
                    .filter(n -> n.getId().getSentDate().after(nowMinusMinutes)).toList();

            if (lastPeriodNotifications.isEmpty() || lastPeriodNotifications.size() < parseInt(STATUS_MAX_REQUESTS)){
                String messageDeliveredStatus = gateway.send(userId, message);
                save(messageDeliveredStatus, userId);
            }
            else {
                log.info("Status notifications have already been sent to this customer in the last minute.");
            }
        }
    }

    @Override
    public void save(String messageDeliveredStatus, Long userId) {
        if (Objects.equals(messageDeliveredStatus, SENT_STATUS_OK))
            repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), STATUS.name()));
    }
}