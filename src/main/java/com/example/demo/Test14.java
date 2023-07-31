package com.example.demo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * @author wesley
 * @description 随机从list中取10个数
 */

@Component
public class Test14 {
    public static void main(String[] args) {
        Map<String, Double> map = new HashMap<>();
        map.put("1", 100D);
        map.put("2", 200D);
        map.put("3", 300D);
        map.put("4", 400D);
        map.forEach((k, v) -> map.put(k, v / 10));
        System.out.println(map);

//        Double d = Double.NaN;
//        double v = NumberUtil.round(d, 2).doubleValue();
//        System.out.println(v);

        User a = new User();
        a.setName("111");
        User b = new User();

//        b = a;//修改b，a也会跟着变
        BeanUtil.copyProperties(a,b);

        System.out.println(a.getName());
        System.out.println(b.getName());

        b.setName("222");
        System.out.println(a.getName());
        System.out.println(b.getName());
    }

    @Data
    static class User {
        private String name;
        private Integer age;
        private String sex;
    }
}

