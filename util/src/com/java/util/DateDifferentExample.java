package com.java.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDifferentExample {
    public static void main(String[] args) {
        //分别定义起止时间
        String startTime = "2019-03-16 06:47:56";
        String stopTime = "2019-03-17 06:47:56";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        Date date1;
        Date date2;
        long diff = 0;
        try {
            date1 = format.parse(startTime);
            date2 = format.parse(stopTime);
            //毫秒ms的差值
            diff = date2.getTime() - date1.getTime();
        }catch (ParseException e){
           e.getStackTrace();
        }
        //将毫秒分别换算成秒，分，小时，天
        long diffSeconds = diff / 1000;
        long diffMinutes =diff / (1000 *  60);
        long diffHours = diff / (1000 * 60 * 60);
        long diffDays = diff / (1000 * 60 * 60 * 24);


        System.out.println("两个时间相差：" +Math.abs(diffSeconds) +"秒");
        System.out.println("两个时间相差：" +Math.abs(diffMinutes) +"分");
        System.out.println("两个时间相差：" +Math.abs(diffHours) +"小时");
        System.out.println("两个时间相差：" +Math.abs(diffDays) +"天");
    }
}
