package com.jas.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2017/12/10.
 */
public class semaphoneTest {
    public static void main(String[] args) throws InterruptedException {
        //只能两个线程并发访问
        final Semaphore semaphone = new Semaphore(2);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i<20;i++){
            final  int No = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphone.acquire();
                        Thread.sleep(1000);
                        System.out.println("任务"+No+"执行结束");
                        semaphone.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
        Thread.sleep(11000);
        System.out.println("执行结束语了");
    }
}
