package com.javaproject.notificationservice.services.Implementation;

import com.javaproject.notificationservice.entity.NotificationEntity;
import com.javaproject.notificationservice.model.Notification;
import com.javaproject.notificationservice.repository.NotificationRepository;
import com.javaproject.notificationservice.services.NotificationService;
import com.javaproject.notificationservice.services.strategy.NotificationStrategy;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final Map<String, NotificationStrategy> strategies;

    private static final  Logger log = getLogger(NotificationServiceImpl.class);

    @Autowired
    public NotificationServiceImpl(Map<String, NotificationStrategy> strategies) {
        this.strategies = strategies;
    }

    @Autowired
    NotificationRepository repository;

    @Override
    public void sendNotification(Notification notification) {
        try {
            NotificationStrategy strategy = strategies.get(notification.getType().toUpperCase());

            if (strategy == null)
                throw new IllegalArgumentException("No strategy found for notification type: " + notification.getType());

            strategy.send(notification.getUserId(), notification.getMessage());
        } catch (IllegalArgumentException ex){
            log.error("Could not send the message to customer %s. Error: %s".formatted(notification.getUserId(), ex));
        }
    }

    @Override
    public List<Notification> getNotifications(Long userId) {
        List<NotificationEntity> notificationEntityList = repository.findAllByIdUserId(userId);

        List<Notification> notifications = new ArrayList<>();
        notificationEntityList.forEach(entity -> notifications.add(Notification.builder()
                        .userId(entity.getId().getUserId())
                        .sentDate(entity.getId().getSentDate())
                        .type(entity.getType()).build()));
        // TODO FALTA O MESSAGE

        return notifications;
    }
}


// TODO LIST
// CRIAR UM MAPPER DE ENTITY PRA MODEL
// COLOCAR O REPOSITORY FORA DO STRATEGY
// ADICIONAR TESTES UNITARIOS
// ADICIONAR SWAGGER

