package com.jas.test;

import java.util.concurrent.locks.LockSupport;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class LockSuppertTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            System.out.println("thread 1 start");
            LockSupport.park();
            System.out.println("thread  1 end");
        });
        thread1.start();
        Thread.sleep(1000);
        System.out.println("main start");
        LockSupport.unpark(thread1);
    }
}
