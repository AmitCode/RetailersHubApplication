package com.intoThe.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class CustomDateTime {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

    public static long findTheDifferenceInMinutes(LocalDateTime date) throws Exception{
        System.out.println("Min");
        Date startDate = dateFormat.parse(date.toString());
        Date endDate = dateFormat.parse(LocalDateTime.now().toString());
        long diffInTime = endDate.getTime() - startDate.getTime();
        return (diffInTime/(1000 * 60) % 60);
    }

    public static long findTheDifferenceInSeconds(LocalDateTime date) throws Exception{
        Date startDate = dateFormat.parse(date.toString());
        Date endDate = dateFormat.parse(LocalDateTime.now().toString());
        long diffInTime = endDate.getTime() - startDate.getTime();
        return (diffInTime/(1000) % 60);
    }

    public static long findTheDifferenceInHours(LocalDateTime date) throws Exception{
        Date startDate = dateFormat.parse(date.toString());
        Date endDate = dateFormat.parse(LocalDateTime.now().toString());
        long diffInTime = endDate.getTime() - startDate.getTime();
        return (diffInTime/(1000 * 60 * 60) % 24);
    }

    public static long findTheDifferenceInYears(LocalDateTime date) throws Exception{
        Date startDate = dateFormat.parse(date.toString());
        Date endDate = dateFormat.parse(LocalDateTime.now().toString());
        long diffInTime = endDate.getTime() - startDate.getTime();
        return (diffInTime/(10001L * 60 * 60 * 24 * 365));
    }

    public static long findTheDifferenceInDays(LocalDateTime date) throws Exception{
        Date startDate = dateFormat.parse(date.toString());
        Date endDate = dateFormat.parse(LocalDateTime.now().toString());
        long diffInTime = endDate.getTime() - startDate.getTime();
        return (diffInTime/(10001L * 60 * 60 * 24) % 365);
    }


}
