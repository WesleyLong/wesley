//package com.example.demo.task;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//import java.util.List;
//
//@Slf4j
//@Component
//public class MyApplicationRunner implements ApplicationRunner {
//
//    @Resource
//    private ScheduledTask scheduledTask;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        log.info("================项目启动初始化定时任务====开始===========");
//        /*
//         * 初始化三个任务：
//         * 1、10秒执行一次
//         * 2、15秒执行一次
//         * 3、20秒执行一次
//         */
//        List<MyTask> tasks = Arrays.asList(
////                MyTask.builder().taskId("10001").expression("*/10 * * * * ?").build()
////                , MyTask.builder().taskId("10002").expression("*/15 * * * * ?").build()
////                , MyTask.builder().taskId("10003").expression("*/20 * * * * ?").build()
//        );
//        scheduledTask.refreshTask(tasks);
//        log.info("================项目启动初始化定时任务====完成==========");
//    }
//}