package com.javaproject.notificationservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ApplicationStartup {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

    @Value("${server.port}")  // Pega a porta configurada ou usa 8080 como padrão
    private String serverPort;

    @PostConstruct
    public void logServerPort() {
        logger.info("Application is running on port: {}", serverPort);
    }


}
