package com.javaproject.notificationservice.utils;

import java.time.LocalDateTime;
import static java.time.ZoneId.systemDefault;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ofPattern;
import java.util.Calendar;
import java.util.Date;


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

    public static Date getDateWithHourMinusOne(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, -1);
        return cal.getTime();
    }

    public static Date getDateWithMinuteMinusOne(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, -1);
        return cal.getTime();
    }
}
