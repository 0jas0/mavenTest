package com.jas.thread;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/12/8.
 */
public class ThreadPoolExecutorTest {
    private static class  Task implements Runnable{
        private int id;
        private String name;

        public Task(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前正在执行的任务"+this);
        }

        @Override
        public String toString() {
            return "Task{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        //会先创建corepoolSize的线程大小，然后放到队列中，最后才创建到达maximumPoolSize的线程，最后执行拒绝的策略
        ThreadPoolExecutor threadPoolExcutor = new ThreadPoolExecutor(1, 2, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue(2), new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("拒绝了这个任务"+r.toString());
            }
        });
        ThreadPoolExecutorTest.Task  task1 = new Task(1,"任务1");
        ThreadPoolExecutorTest.Task  task2 = new Task(2,"任务2");
        ThreadPoolExecutorTest.Task  task3 = new Task(3,"任务3");
        ThreadPoolExecutorTest.Task  task4 = new Task(4,"任务4");
        ThreadPoolExecutorTest.Task  task5 = new Task(5,"任务5");

        threadPoolExcutor.execute(task1);
        threadPoolExcutor.execute(task2);
        threadPoolExcutor.execute(task3);
        threadPoolExcutor.execute(task4);
        threadPoolExcutor.execute(task5);

        threadPoolExcutor.shutdown();
    }
}
