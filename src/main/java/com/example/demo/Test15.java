package com.example.demo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.example.demo.util.DateUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author wesley
 * @description 随机从list中取10个数
 */

@Component
public class Test15 {
    public static void main(String[] args) {
        Object[][] data = {{"Alice", 30}, {"Bob", 25}, {"Cathy", 35}, {"David", 28}};

        Arrays.sort(data, (o1, o2) -> {
            Integer age1 = (Integer) o1[1];
            Integer age2 = (Integer) o2[1];
            return age1.compareTo(age2);
        });

        for (Object[] row : data) {
            System.out.println(Arrays.toString(row));
        }
    }
}

