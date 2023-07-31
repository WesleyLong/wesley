package com.example.demo;

import java.util.Arrays;


/**
 * 二维数组排序
 * 可以在1维放名称 2维放数字类型 然后用2维进行比较和排序
 */
public class Test8 {
    public static void main(String[] args) {
        String[] names = {"a", "b", "c", "d", "e", "f", "g", "i", "j", "k", "l", "m", "n"};
        int[] heights = {1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(sortNamesByHeight(names, heights)));

    }

    static String[] sortNamesByHeight(String[] names, int[] heights) {
        int n = names.length;
        String[][] people = new String[n][2];

        // 将每个人的名字和身高存储到二维数组中
        for (int i = 0; i < n; i++) {
            people[i][0] = names[i];
            people[i][1] = Integer.toString(heights[i]);
        }

        // 根据身高进行排序（降序）
        Arrays.sort(people, (a, b) -> Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])));

        // 获取排序后的名字数组
        String[] sortedNames = new String[n];
        for (int i = 0; i < n; i++) {
            sortedNames[i] = people[i][0];
        }

        return sortedNames;
    }
}




