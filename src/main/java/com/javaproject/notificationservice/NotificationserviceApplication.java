package com.javaproject.notificationservice;

import com.javaproject.notificationservice.gateway.Gateway;
import com.javaproject.notificationservice.services.Implementation.StatusNotificationService;
import com.javaproject.notificationservice.services.NotificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationserviceApplication.class, args);
		System.out.println("Notification service is running...");

		NotificationService service = new StatusNotificationService(new Gateway());
        
        service.send("news", "user", "news 1");
        service.send("news", "user", "news 2");
        service.send("news", "user", "news 3");
        service.send("news", "another user", "news 1");
        service.send("update", "user", "update 1");
	}

}
