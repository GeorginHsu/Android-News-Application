package com.example.myapplication3.DataDealer;

import java.util.Calendar;
import java.util.Date;

public class GetURLString {
    public static String GetString(String categories,String words,String startDate,String endDate)
    {

        int size = 10;

        // 读取当前日期，传入nowDate
        // 获取当前日期
        Date currentDate = new Date();
        // 获取 Calendar 实例
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        // 获取年份、月份和日期

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // 注意：Calendar.MONTH 返回的月份是从 0 开始的，所以需要加 1
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String defaultEndDate = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", dayOfMonth);

        String kong = "";
        if(endDate.equals(kong)==true) endDate=defaultEndDate;

        String finalURL;
        finalURL ="https://api2.newsminer.net/svc/news/queryNewsList?size="+size+"&startDate="+startDate+"&endDate="+endDate+"&words="+words+"&categories="+categories+"&page=";
        return finalURL;
    }
}
