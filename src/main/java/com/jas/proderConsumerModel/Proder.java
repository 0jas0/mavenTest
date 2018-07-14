package com.jas.proderConsumerModel;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/12/7.
 */
public class Proder implements Runnable {
    private LinkedBlockingQueue<Task> queue;

    private AtomicInteger autoInteger = new AtomicInteger(0);

    private volatile  boolean flag = true;
    public Proder(LinkedBlockingQueue<Task> queue) {
        this.queue = queue;
    }

    public void run() {
        Random random = new Random();
        while (flag){
            try {
                Task task = new Task();
                task.setId(autoInteger.getAndAdd(1));
                task.setName("任务NO."+autoInteger.get());
                if(queue.offer(task,2, TimeUnit.SECONDS)){
                    System.out.println("生产者向队列中添加任务"+task);
                    Thread.sleep( random.nextInt(1000));
                }else {
                    System.out.println("生产者向队列中添加任务失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        flag = false;
    }
}
