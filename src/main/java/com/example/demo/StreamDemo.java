package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    static List<Student> students = Arrays.asList(
            new Student("小王", 10, 1006, 1, null),
            new Student("小李", 11, 1005, 1, null),
            new Student("小陈", 12, 1004, 0, null),
            new Student("小明", 13, 1003, 1, null),
            new Student("小红", 14, 1002, 0, null),
            new Student("小张", 15, 1001, 1, null),
            new Student("小王", 16, 1000, 0, null)
    );
    static List<Integer> numbers = Arrays.asList(
            1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1
    );

    public static void main(String[] args) {
        long before = System.currentTimeMillis();

        //循环遍历
        students.stream().forEach(x -> System.out.println(x.getName()));
        students.stream().forEach(student -> System.out.println(student));
        students.stream().forEach(System.out::println);

        //去重
        //默认根据Object的equals去重 这里重写了相等的逻辑 根据name去重
        List<Student> list1 = students.stream().distinct().collect(Collectors.toList());
        numbers.stream().distinct().forEach(System.out::println);

        //筛选过滤
        students.stream().filter(x -> x.getAge() > 12).forEach(System.out::println);
        students.stream().filter(x -> x.getAge() > 12 && x.getGender() == 0).forEach(System.out::println);
        //年龄>12的第一个对象
        Student student1 = students.stream().filter(x -> x.getAge() > 12).findFirst().orElse(null);

        //排序
        //按照ID排序
        students.stream().sorted(Comparator.comparing(Student::getId)).collect(Collectors.toList())
                .forEach(x -> System.out.println(x.getId() + x.getName()));
        //按照年龄排序
        students.sort(Comparator.comparing(Student::getAge).reversed());
        //按照map中的value排序
        Map<String, Integer> map = new HashMap<>();
        map.put("xiaoming", 2);
        map.put("xiaohong", 1);
        map.put("xiaogang", 4);
        map.put("xiaoli", 3);
        //排序后转化为list 此时 list的下标就是他们的排名 注意下标要+1
        List<Map.Entry<String, Integer>> sortedList = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());//正序
        List<Map.Entry<String, Integer>> reverseList = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList());//倒序
        for (int i = 0; i < sortedList.size(); i++) {
            System.out.println(i + 1);
            System.out.println(sortedList.get(i).getKey());
            System.out.println(sortedList.get(i).getValue());
            System.out.println("---------");
        }
        for (int i = 0; i < reverseList.size(); i++) {
            System.out.println(i + 1);
            System.out.println(reverseList.get(i).getKey());
            System.out.println(reverseList.get(i).getValue());
            System.out.println("---------");
        }


        //分组
        //按gender分组
        Map<Integer, List<Student>> map1 = students.stream().collect(Collectors.groupingBy(Student::getGender));
        //按gender分组后 求每组的年龄之和
        Map<Integer, Integer> map2 = students.stream().collect(Collectors.groupingBy(Student::getGender, Collectors.summingInt(Student::getAge)));
        //按gender分组后 求每组的数量
        Map<Integer, Long> map3 = students.stream().collect(Collectors.groupingBy(Student::getGender, Collectors.counting()));
        //多重分组
        //先根据年龄分组，再根据性别分组
        Map<Integer, Map<Integer, List<Student>>> collect1 = students.stream().collect(Collectors.groupingBy(Student::getAge, Collectors.groupingBy(Student::getGender)));
        //分区
        //分成两部分，一部分大于10岁，一部分小于等于10岁
        Map<Boolean, List<Student>> partMap = students.stream().collect(Collectors.partitioningBy(v -> v.getAge() > 10));


        //转化为map
        //1-将原有list转化为以name为主键,实体类为值的map
        //如果作为主键的值重复的话,会报错，所以要确保主键唯一,或者采取下面第二种方法
//        Map<String, Student> studentMap = students.stream().collect(Collectors.toMap(Student::getName, Function.identity()));
        //2-取分组后的每个list的第一个元素作为value，避免了上面的主键重复的情况，但会存在数据丢失
        Map<String, Student> studentMap_2 = students.stream().collect(Collectors.groupingBy(Student::getName,
                Collectors.collectingAndThen(Collectors.toList(), students -> students.get(0))));
        studentMap_2.keySet().forEach(x -> System.out.println(x + ":" + studentMap_2.get(x)));
        //3-先把实体类的list映射成name的list，然后过滤找出名字是小明的name
        List<String> collect = students.stream().map(Student::getName).filter(x -> x.equalsIgnoreCase("小明")).collect(Collectors.toList());

        //排名变化
        //初始排名
        Map<String, Integer> map_before = new HashMap<>();
        for (int i = 0; i < students.size(); i++) {
            map_before.put(students.get(i).getName(), i);
        }
        map_before.keySet().forEach(x -> System.out.println(x + ":" + map_before.get(x)));
        //变化后排名
        students.sort(Comparator.comparing(Student::getId).reversed());
        Map<String, Integer> map_after = new HashMap<>();
        for (int i = 0; i < students.size(); i++) {
            map_after.put(students.get(i).getName(), i);
        }
        map_after.keySet().forEach(x -> System.out.println(x + ":" + map_after.get(x)));
        //排名变化值
        Map<String, Integer> rank = new HashMap<>();
        for (String area : map_before.keySet()) {
            rank.put(area, map_before.get(area) - map_after.get(area));
        }
        rank.keySet().forEach(x -> System.out.println(x + ":" + rank.get(x)));

        //把对应的gender映射成1-男 0-女
        Map<Integer, String> genderMap = new HashMap<>();
        genderMap.put(1, "男");
        genderMap.put(0, "女");
        students.forEach(x -> x.setGenderName(genderMap.get(x.getGender())));

        //匹配
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        //所有的元素都符合条件为true，否则为false
        boolean allMatch = list.stream().allMatch(e -> e > 10); //false
        //与上面相反
        boolean noneMatch = list.stream().noneMatch(e -> e > 10); //true
        //有任一符合为true，一个都没有为false
        boolean anyMatch = list.stream().anyMatch(e -> e > 4);  //true

        list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);

        //归约
        //传入一个两个入参的函数，第一次取list中的前两个元素作为函数入参计算，第二次则把第一次的结果和第三个元素作为入参，以此类推
        Integer v1 = list.stream().reduce(10, (x1, x2) -> x1 + x2 * 10);
        System.out.println(v1);

        //下面两个是串行流和并行流的区别
        //很玄妙，暂时还没看懂
        Integer v2 = list.stream().reduce(0,
                (x1, x2) -> {
                    System.out.println("stream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {//不起作用，想如此操作必须用下面的并行流
                    System.out.println("stream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 * x2;
                });
        System.out.println(v2);

        Integer v3 = list.parallelStream().reduce(0,
                (x1, x2) -> {
                    System.out.println("parallelStream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {
                    System.out.println("parallelStream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 * x2;
                });
        System.out.println(v3);

        long after = System.currentTimeMillis();
        System.out.println((after - before) + "ms");
    }

    @Data
    @AllArgsConstructor
    static
    class Student {
        String name;
        Integer age;
        Integer id;
        Integer gender;
        String genderName;

        @Override
        public int hashCode() {
            return this.getName().hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Student)) {
                return false;
            }
            Student student = (Student) o;
            if (this == student) {
                return true;
            }
            return student.getName().equalsIgnoreCase(this.getName());
        }
    }
}
