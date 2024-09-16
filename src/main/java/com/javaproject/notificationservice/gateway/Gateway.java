package com.javaproject.notificationservice.gateway;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static com.javaproject.notificationservice.utils.ConstantsUtils.SENT_STATUS_OK;
import static org.slf4j.LoggerFactory.getLogger;

@Component
public class Gateway {
    private static final Logger log = getLogger(Gateway.class);

    public String send(Long userId, String message) {
        log.info("sending message to user {}", userId);
        return SENT_STATUS_OK;
    }
}