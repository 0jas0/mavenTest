package com.jas.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class curatorPractice {

    /**
     *  可重入锁测试
     */
    public static void interProcessMutexTest(){
        CuratorFramework curatorFramework = curatorConnection.getInstance();
        final InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/a");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (lock.acquire(1, TimeUnit.MILLISECONDS)) {
                        // 获取锁成功
                        System.out.println("获取锁成功");
                        Thread.sleep(20);
                        // 这里可以进来
                        if (lock.acquire(1, TimeUnit.MILLISECONDS)){
                            System.out.println("再次获得锁");
                            lock.release();
                            System.out.println("释放锁了");
                        }
                        lock.release();
                        System.out.println("再次释放锁了");

                    } else {
                        System.out.println("获取锁失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 10; i++){
            executorService.execute(runnable);
        }

    }

    /**
     * 不可重入锁
     */
    public static void interProcessSemaphoreMutexTest(){
        CuratorFramework curatorFramework = curatorConnection.getInstance();
        final InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(curatorFramework, "/a");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (lock.acquire(1, TimeUnit.MILLISECONDS)) {
                        // 获取锁成功
                        System.out.println("获取锁成功");
                        Thread.sleep(20);
                        // 这里不能进来
                        if (lock.acquire(1, TimeUnit.MILLISECONDS)){
                            System.out.println("再次获得锁");
                            lock.release();
                            System.out.println("释放锁了");
                        }
                        lock.release();
                        System.out.println("再次释放锁了");
                    } else {
                        System.out.println("获取锁失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 10; i++){
            executorService.execute(runnable);
        }

    }

    public static void interProcessReadWriteLockTest(){
        CuratorFramework curatorFramework = curatorConnection.getInstance();
        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(curatorFramework, "/a");
        // 读锁
        final InterProcessMutex readLock = readWriteLock.readLock();
        // 写锁
        final InterProcessMutex writeLock = readWriteLock.writeLock();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {

                    if (writeLock.acquire(1, TimeUnit.MILLISECONDS)){
                        System.out.println("获取写锁成功");
                        Thread.sleep(20);
                        writeLock.release();
                    }

                    if (readLock.acquire(1, TimeUnit.MILLISECONDS)) {
                        System.out.println("获取读锁成功");
                        Thread.sleep(20);
                        readLock.release();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 10; i++){
            executorService.execute(runnable);
        }

    }


    public static void main(String[] args) {
        // 可重入锁测试
        //interProcessMutexTest();

        // 不可重入锁测试
        //interProcessSemaphoreMutexTest();

        // 读些锁
        interProcessReadWriteLockTest();

    }
}
