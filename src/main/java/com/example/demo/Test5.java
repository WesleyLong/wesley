package com.example.demo;

import com.google.common.base.Joiner;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test5 {
    public static void main(String[] args) {
        f1();
        f2();
    }

    static void f1() {
        String s = "hello";
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        for (byte b : bytes) {//104, 101, 108, 108, 111
            System.out.print(b + ",");
        }
    }

    static void f2() {
        byte[] bytes = {104, 101, 108, 108, 111};
        String s = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(s);
    }
}
