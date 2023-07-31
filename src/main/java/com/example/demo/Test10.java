package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wang yaowen
 * @version 1.0
 * @date 2020/5/31 下午12:34
 */

@Component
public class Test10 {
    public static void main(String[] args) throws ParseException {
        System.out.println(romanToInt("MCMXCIV"));
    }
    static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int currentNum = map.get(s.charAt(i));
            int nextNum = i < s.length() - 1 ? map.get(s.charAt(i + 1)) : 0;
            if (currentNum >= nextNum) {
                result += currentNum;
            } else {
                result -= currentNum;
            }
            System.out.println(result);
        }
        return result;
    }

}

