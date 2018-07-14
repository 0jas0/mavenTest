package com.jas.FutureModel;

import java.util.concurrent.*;


/**
 * Created by Administrator on 2017/12/10.
 */
public class jdkSelfFuture implements Callable<String>{

    private String data;

    public jdkSelfFuture(String data) {
        this.data = data;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        return "已经处理完任务："+data;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new jdkSelfFuture("任务1"));
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(futureTask);
        System.out.println("开始执行任务了");
        String result = futureTask.get();
        System.out.println(result);
        executorService.shutdown();

    }

}
