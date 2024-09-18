package com.javaproject.notificationservice;

import com.javaproject.notificationservice.services.NotificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class NotificationserviceApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = SpringApplication.run(NotificationserviceApplication.class, args);
		System.out.println("Notification service is running...");

		NotificationService service = context.getBean(NotificationService.class);
		service.send("marketing", 1L, "marketing 1");
		service.send("marketing", 1L, "marketing 2");
		service.send("marketing", 1L, "marketing 3");
		service.send("update", 1L, "update 1");
        service.send("news", 1L, "news 2");
        service.send("news", 1L, "news 3");
        service.send("news", 2L, "news 1");
	}
}
