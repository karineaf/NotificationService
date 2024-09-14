package com.javaproject.notificationservice.utils;

public enum NotificationType {
    MARKETING("marketing"),
    NEWS("news"),
    STATUS("status");

    private final String descrption;

    NotificationType(String descrption) {
        this.descrption = descrption;
    }

    public String getDescrption() {
        return descrption;
    }
}
