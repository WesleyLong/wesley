package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 正则表达式
 */

@Component
public class Test12 {
    public static void main(String[] args) {
        String REGEX = "[^0-9]";
        String ticketStr = "0-923.45454...1Cd2ada小明！@#￥……&*";
        String ticket = Pattern.compile(REGEX).matcher(ticketStr).replaceAll("").trim();
        System.out.println(ticket);
    }
}

