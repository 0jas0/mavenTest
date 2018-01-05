package com.jas.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/11/29.
 */
public class countDownLatch {
    public static int  num =0;

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch count = new CountDownLatch(1);
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (num<=10) {
                    countDownLatch.num++;
                    System.out.println(Thread.currentThread().getName() + "加一了");
                    if (num == 5) {
                        System.out.println("发出通知了");
                        count.countDown();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                if (num!=5){
                    try {
                        count.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.printf("接收到通知");
            }
        },"t2");
        t2.start();
        t1.start();
    }
}
