package com.javaproject.notificationservice.controller;


import com.javaproject.notificationservice.model.Notification;
import com.javaproject.notificationservice.services.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create notification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notification was sent successfully", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @PostMapping()
    public ResponseEntity<Notification> sendNotification(@RequestBody Notification product) {
           notificationService.sendNotification(product);
        return new ResponseEntity<>(CREATED);
    }

    @Operation(summary = "Get notifications by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found notifications",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Notification.class, type = "array"))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Notifications not found", content = @Content)})
    @GetMapping()
    public List<Notification> getNotifications(@RequestParam(name = "userId") long userId) {
        return notificationService.getNotifications(userId);
    }

}
