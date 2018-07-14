package com.jas.concurrent;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/12/5.
 */
public class countDownLatch {
    public static void main(String[] args) {
        //final Object lock = new Object();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final LinkedList<String> list = new LinkedList<>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    //synchronized (lock){
                        String str = Thread.currentThread().getName()+"添加了一个元素"+i;
                        list.add(str);
                        System.out.println(str);
                        if(list.size()==5){
                            System.out.println(Thread.currentThread().getName()+"发送通知了");
                            //lock.notify();
                            countDownLatch.countDown();
                        }
                    //}
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //synchronized (lock){
                    if(list.size()!=5){
                        try {
                            countDownLatch.await();
                           // lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName()+"接受到信息");
                //}
            }
        },"t2");
        t2.start();
        t1.start();
    }
}
