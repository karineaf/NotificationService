package com.javaproject.notificationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;


@RestController
public class HealthController {


    @GetMapping("/")
    public ResponseEntity<?> ebHealth() {
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return new ResponseEntity<>(OK);
    }
}
