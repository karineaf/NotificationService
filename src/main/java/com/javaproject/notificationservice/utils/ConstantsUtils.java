package com.javaproject.notificationservice.utils;

import com.javaproject.notificationservice.model.Notification;

import static java.time.Duration.*;

public final class ConstantsUtils {

    private ConstantsUtils() {
        throw new AssertionError("Cannot instantiate a final class - Constants");
    }

    public static final Notification MARKETING = new Notification("marketing", ofHours(1), 3);
    public static final Notification NEWS = new Notification("news", ofDays(1), 1);
    public static final Notification STATUS = new Notification("status", ofMinutes(1), 2);


    public static final int MARKETING_MAX_REQUESTS = 3;
    public static final int STATUS_MAX_REQUESTS = 2;
    public static final String SENT_STATUS_OK = "OK";

}
