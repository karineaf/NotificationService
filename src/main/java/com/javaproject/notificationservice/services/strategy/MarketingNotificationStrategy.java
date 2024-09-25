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
import static com.javaproject.notificationservice.utils.NotificationType.MARKETING;
import static java.lang.Integer.parseInt;
import static org.slf4j.LoggerFactory.getLogger;

@Component("MARKETING")
public class MarketingNotificationStrategy implements NotificationStrategy{

    @Value("${marketing.max.requests}")
    private static String MARKETING_MAX_REQUESTS;

    @Value("${marketing.period.requests}")
    private static String MARKETING_PERIOD_REQUESTS;

    @Autowired
    private Gateway gateway;

    @Autowired
    private NotificationRepository repository;

    private static final Logger log = getLogger(MarketingNotificationStrategy.class);

    @Override
    public void send(Long userId, String message) {

        Date nowMinusMinutes = getDateMinusMinutes(new Date(), parseInt(MARKETING_PERIOD_REQUESTS));

        List<NotificationEntity> notifications = repository.findAllByIdUserIdAndType(userId, MARKETING.name());

        if (notifications.isEmpty()) {
            String messageDeliveredStatus = gateway.send(userId, message);
            save(messageDeliveredStatus, userId);

        } else {
            List<NotificationEntity> lastPeriodNotifications = notifications.stream()
                    .filter(n -> n.getId().getSentDate().after(nowMinusMinutes)).toList();

            if (lastPeriodNotifications.isEmpty() || lastPeriodNotifications.size() < parseInt(MARKETING_MAX_REQUESTS)){
                String messageDeliveredStatus = gateway.send(userId, message);
                save(messageDeliveredStatus, userId);
            }
            else {
                log.info("Marketing notifications have already been sent in the last 3 hours");
            }
        }
    }

    @Override
    public void save(String messageDeliveredStatus, Long userId) {
        if (Objects.equals(messageDeliveredStatus, SENT_STATUS_OK))
            repository.save(new NotificationEntity(new NotificationKeyEntity(userId, new Date()), MARKETING.name()));
    }
}
