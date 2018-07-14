package com.jas.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/12/8.
 */
public class ScheduledTreadPoolTest implements Runnable{

    public void run() {
        System.out.println("测试");
    }

    public static void main(String[] args) {
        ScheduledTreadPoolTest task = new ScheduledTreadPoolTest();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        //延迟1秒后执行run方法，以后每3秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(task,1,3,TimeUnit.SECONDS);
    }


}
