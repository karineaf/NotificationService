package com.javaproject.notificationservice;

import com.javaproject.notificationservice.services.NotificationService;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import static org.slf4j.LoggerFactory.getLogger;


@SpringBootApplication
public class NotificationserviceApplication {

	private static final Logger log = getLogger(NotificationserviceApplication.class);

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = SpringApplication.run(NotificationserviceApplication.class, args);
		log.info("Notification service is running...");

		NotificationService service = context.getBean(NotificationService.class);
		service.send("marketing", 1L, "marketing 1");
		service.send("marketing", 1L, "marketing 2");
		service.send("marketing", 1L, "marketing 3");
		service.send("marketing", 1L, "marketing 4");
		service.send("update", 1L, "update 1");
        service.send("news", 1L, "news 2");
        service.send("news", 1L, "news 3");
        service.send("news", 2L, "news 1");
	}
}
