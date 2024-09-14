package com.javaproject.notificationservice.gateway;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;


public class Gateway {
    private static final Logger log = getLogger(Gateway.class);

    public void send(String userId, String message) {
        log.info("sending message to user {}", userId);
    }
}