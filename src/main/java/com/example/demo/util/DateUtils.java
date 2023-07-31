package com.example.demo.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 *
 * @author qingyuan
 */
public class DateUtils {
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 中文日期转date
     *
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy年M月dd日");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取最近一年年月
     */
    public static List<String> getThisYearMonths() {
        //建一个容器
        List<String> months = new ArrayList<>();
        //获取日历对象
        Calendar calendar = Calendar.getInstance();
        //调整到12个月以前
        calendar.add(Calendar.MONTH, -11);
        //循环12次获取12个月份
        for (int i = 0; i < 12; i++) {
            //日历对象转为Date对象
            Date date = calendar.getTime();
            //将date转为字符串
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String dateStr = sdf.format(date);
            //向list集合中添加
            months.add(dateStr);
            //每次月份+1
            calendar.add(Calendar.MONTH, 1);
        }
        return months;
    }

    /**
     * 获取最近一年年月
     */
    public static List<String> getCurrentYearMonths() {
        //建一个容器
        List<String> months = new ArrayList<>();
        //获取日历对象
        Calendar calendar = Calendar.getInstance();
        int month = DateUtil.thisMonth();
        calendar.add(Calendar.MONTH, -month);
        for (int i = 0; i < month; i++) {
            //日历对象转为Date对象
            Date date = calendar.getTime();
            //将date转为字符串
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String dateStr = sdf.format(date);
            //向list集合中添加
            months.add(dateStr);
            //每次月份+1
            calendar.add(Calendar.MONTH, 1);
        }
        return months;
    }

    /**
     * 获取指定年月的，月份的最后一天的字符串
     *
     * @param year
     * @param month
     * @return
     */
    public static String getDateLastDay(String year, String month) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        return format.format(calendar.getTime());
    }

    /**
     * 获取当前月的上一个月
     *
     * @return
     */
    public static String getLastMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        date = calendar.getTime();
        String accDate = sdf.format(date);
        return accDate;
    }

    /**
     * 获取当前月的上一个月内的所有天
     *
     * @return
     */
    public static List<String> getLastMonthDays() {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        List<String> dateList = new ArrayList<String>();
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = new Date();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -1);
            Date dateEnd = calendar.getTime();

            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -1);
            String startDay = sdf.format(calendar.getTime());
            dateList.add(startDay);
            while (dateEnd.after(calendar.getTime())) {
                calendar.add(Calendar.DATE, 1);
                dateList.add(sdf.format(calendar.getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateList;
    }

    /**
     * 获取当前月的上一个月内的所有天
     *
     * @return
     */
    public static List<String> getLastMonths() {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM);
        List<String> dateList = new ArrayList<String>();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -11);
            //循环12次获取12个月份
            for (int i = 0; i < 12; i++) {
                //日历对象转为Date对象
                Date date = calendar.getTime();
                //将date转为字符串
                String dateStr = sdf.format(date);
                //向list集合中添加
                dateList.add(dateStr);
                //每次月份+1
                calendar.add(Calendar.MONTH, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateList;
    }

    public static void main(String[] args) {
        List<String> thisYearMonths = getThisYearMonths();
        System.out.println(thisYearMonths);
        List<String> lastMonthDays = getLastMonths();
        for (String lastMonthDay : lastMonthDays) {
            System.out.println(lastMonthDay);
        }

        String format = DateUtil.format(DateTime.now(), DatePattern.NORM_MONTH_PATTERN);
        System.out.println("====" + format);
    }


    /**
     * 获取当前日期day
     *
     * @return
     */
    public static int getDay(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int result = calendar.get(Calendar.DATE);
            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取当前日期month
     *
     * @return
     */
    public static int getMonth(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int result = calendar.get(Calendar.MONTH) + 1;
            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
