package com.javaproject.notificationservice.model;


import java.time.Duration;

public class Notification {

    private final String type;
    private final Duration duration;
    private final Integer maxRequests;

    public Notification(String type, Duration duration, Integer maxRequests) {
        this.type = type;
        this.duration = duration;
        this.maxRequests = maxRequests;
    }

    public Duration getDuration() {
        return duration;
    }

    public Integer getMaxRequests() {
        return maxRequests;
    }

    public String getType() {
        return type;
    }
}
