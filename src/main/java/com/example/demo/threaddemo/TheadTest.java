package com.example.demo.threaddemo;

public class TheadTest {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.start();

        Thread thread2 = new Thread(new Thread2());
        thread2.start();

        Thread thread3 = new Thread(() -> System.out.println("hello thread3"));
        thread3.start();
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println("hello thread1");
        }
    }

    static class Thread2 implements Runnable {
        @Override
        public void run() {
            System.out.println("hello thread2");
        }
    }
}
