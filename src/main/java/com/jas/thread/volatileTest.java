package com.jas.thread;

import java.util.concurrent.CountDownLatch;

public class volatileTest {
    private volatile int i = 0;
    public static void main(String[] args) throws InterruptedException {
        final volatileTest volatileTest = new volatileTest();
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i= 0; i < 10000; i++){
                    volatileTest.i++;
                }
                countDownLatch.countDown();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for (int i= 0; i < 10000; i++){
                    volatileTest.i++;
                }
                countDownLatch.countDown();
            }
        });
        thread1.start();
        thread2.start();
        countDownLatch.await();
        System.out.println(volatileTest.i);
    }
}
