package com.jas.MasterWorkerModel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Administrator on 2017/12/7.
 */
public class Master {
    //装任务的并发队列
    private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<>();
    //装所有的worker
    private HashMap<String,Thread> workerHashMap = new HashMap<>();
    //装所有worker执行的结果
    private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();
    public Master(Worker worker,int count){
        for(int i=0;i<count;i++){
            String workName = "workerNo."+i;
            worker.setTaskQueue(taskQueue);
            worker.setResultMap(resultMap);
            workerHashMap.put(workName,new Thread(worker,workName));
        }
    }
    //添加任务
    public void submitTask(Task task){
        taskQueue.add(task);
    }

    //启动所有的worker
    public void begin(){
        for(Map.Entry<String,Thread> entry : workerHashMap.entrySet()){
            entry.getValue().start();
        }
    }

    //判断所有的任务是否执行结束
    public boolean isEnd(){
        for(Map.Entry<String,Thread> entry : workerHashMap.entrySet()){
            Thread thread = entry.getValue();
            if(thread.getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }
    //返回最终任务的执行结果集
    public ConcurrentHashMap<String,Object> getResultMap(){
        return this.resultMap;
    }

}
