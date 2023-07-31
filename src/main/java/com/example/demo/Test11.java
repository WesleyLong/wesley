package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wang yaowen
 * @version 1.0
 * @date 2020/5/31 下午12:34
 */

@Component
public class Test11 {
    public static void main(String[] args) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH,20);
        cal.add(Calendar.MONTH,12);
        int year = cal.get(Calendar.YEAR);//获取年份
        int month = cal.get(Calendar.MONTH) + 1;//获取月份
        int day = cal.get(Calendar.DAY_OF_MONTH);//获取日
        int hour = cal.get(Calendar.HOUR_OF_DAY);//小时
        int minute = cal.get(Calendar.MINUTE);//分
        int second = cal.get(Calendar.SECOND);//秒
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);//一周的第几天
        System.out.println("现在的时间是：公元" + year + "年" + month + "月" + day + "日      " + hour + "时" + minute + "分" + second + "秒       星期" + WeekOfYear);

    }
}

