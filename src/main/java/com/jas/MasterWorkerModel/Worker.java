package com.jas.MasterWorkerModel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Administrator on 2017/12/7.
 */
public class Worker implements Runnable {
    //每一个worker都有master的taskQueue的引用
    private ConcurrentLinkedQueue<Task>      taskQueue;
    //每一个worker都有master的resultMap的引用
    private ConcurrentHashMap<String,Object> resultMap;

    public void setTaskQueue(ConcurrentLinkedQueue<Task> taskQueue){
        this.taskQueue = taskQueue;
    }

    public void setResultMap(ConcurrentHashMap<String,Object> resultMap){
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true){
            Task task = taskQueue.poll();
            if(task==null) break;
            Object result = handle(task);
            resultMap.put(task.getName()+task.getId(),result);
        }
    }
    //处理任务
    public Object handle(Task task){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "放回结果";
    }

}
