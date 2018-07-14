package com.jas.proderConsumerModel;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/12/7.
 */
public class Client {
    public static void main(String[] args) {
        LinkedBlockingQueue<Task> queue = new LinkedBlockingQueue<Task>();
        Proder proder = new Proder(queue);
        Proder proder1 = new Proder(queue);
        Proder proder2 = new Proder(queue);
        Consumer consumer = new Consumer(queue);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(proder);
        executor.execute(proder1);
        executor.execute(proder2);
        executor.execute(consumer);
        executor.execute(consumer1);
        executor.execute(consumer2);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        proder.stop();
        proder1.stop();
        proder2.stop();
        executor.shutdown();
    }
}
