package com.javaproject.notificationservice.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static java.time.format.DateTimeFormatter.ofPattern;
import static java.time.ZoneId.systemDefault;


public class DateUtils {


    private static final DateTimeFormatter FORMATTER = ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");

    public static String formatDate(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), systemDefault());

        return localDateTime.format(FORMATTER);
    }

    public static int getDayOfYear(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    public static Date getDateMinusMinutes(Date date, int minutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, -minutes);
        return cal.getTime();
    }
}
