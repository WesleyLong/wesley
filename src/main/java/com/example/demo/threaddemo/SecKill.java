package com.example.demo.threaddemo;

import lombok.SneakyThrows;
import lombok.Synchronized;

import static java.lang.Thread.sleep;

public class SecKill {
    int total = 10;

    public void secKill() {
        Runnable task = () -> {
            while (total > 0) {
                synchronized (this) {
                    if(total < 1){
                        System.out.println("已售完");
                        break;
                    }
                    total--;
                    System.out.println(Thread.currentThread().getName() + ",抢到了1个手机，还剩" + total + "个。");
                }
            }
        };
        Thread thread1 = new Thread(task, "线程 1");
        Thread thread2 = new Thread(task, "线程 2");
        Thread thread3 = new Thread(task, "线程 3");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void main(String[] args) {
        SecKill secKill = new SecKill();
        secKill.secKill();
    }
}
