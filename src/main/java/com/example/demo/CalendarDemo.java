package com.example.demo;


import java.util.Calendar;
import java.util.Date;

public class CalendarDemo {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println("year:" + calendar.get(Calendar.YEAR));
        System.out.println("month:" + (calendar.get(Calendar.MONTH) + 1));//月要加1
        System.out.println("day:" + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("week-year:" + calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("week-month:" + calendar.get(Calendar.WEEK_OF_MONTH));
    }
}
