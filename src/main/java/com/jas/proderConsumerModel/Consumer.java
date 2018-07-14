package com.jas.proderConsumerModel;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2017/12/7.
 */
public class Consumer implements Runnable{
    private LinkedBlockingQueue<Task> queue;

    public Consumer(LinkedBlockingQueue<Task> queue) {
        this.queue = queue;
    }

    public void run() {
        Random random = new Random();
        while (true){
            try {
                Task task = queue.take();
                System.out.println("消费者从队列中取走任务"+task);
                Thread.sleep( random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
