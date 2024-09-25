package com.javaproject.notificationservice.controller;


import com.javaproject.notificationservice.model.Notification;
import com.javaproject.notificationservice.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping()
    public ResponseEntity<Notification> sendNotification(@RequestBody Notification product) {
           notificationService.sendNotification(product);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping()
    public List<Notification> getNotifications(@RequestParam(name = "userId") long userId) {
        return notificationService.getNotifications(userId);
    }

}
