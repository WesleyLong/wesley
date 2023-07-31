package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author wesley
 * @description 随机从list中取10个数
 */

@Component
public class Test13 {
    public static void main(String[] args) {
        List<Integer> oldList = new ArrayList<>();
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int index = random.nextInt(oldList.size());
            newList.add(oldList.get(index));
            oldList.remove(index);
        }
    }
}

