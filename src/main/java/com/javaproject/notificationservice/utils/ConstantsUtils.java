package com.javaproject.notificationservice.utils;


import org.springframework.beans.factory.annotation.Value;

public final class ConstantsUtils {

    private ConstantsUtils() {
        throw new AssertionError("Cannot instantiate a final class - Constants");
    }

    public static final int MARKETING_MAX_REQUESTS = 3;
    public static final int STATUS_MAX_REQUESTS = 2;
    public static final String SENT_STATUS_OK = "OK";

}
