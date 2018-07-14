package com.jas.concurrent;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/12/5.
 */
public class wait_notify1{
    public static void main(String[] args) {
        final Object lock = new Object();
        final LinkedList<String> list = new LinkedList<>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    synchronized (lock){
                        String str = Thread.currentThread().getName()+"添加了一个元素"+i;
                        list.add(str);
                        System.out.println(str);
                        if(list.size()==5){
                            System.out.println(Thread.currentThread().getName()+"发送通知了");
                            lock.notify();
                        }
                    }
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    if(list.size()!=5){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName()+"接受到信息");
                }
            }
        },"t2");
        t2.start();
        t1.start();
    }
}
