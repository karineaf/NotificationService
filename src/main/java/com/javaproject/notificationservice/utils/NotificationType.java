package com.javaproject.notificationservice.utils;

public enum NotificationType {
    MARKETING,
    NEWS,
    STATUS;


    NotificationType() {
    }


    public static NotificationType fromString(String text){
        return NotificationType.valueOf(text.toUpperCase().trim());
    }

}
