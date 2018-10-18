package com.jas.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.locks.*;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class curatorPractice {

    public static  ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     *  可重入锁测试
     *  acquire()获得锁，并提供超时机制；通过release()释放锁。
     *  makeRevocable(RevocationListener<T> listener)定义了可协商的撤销机制，当别的进程或线程想让你释放锁时，listener会被调用。
     */
    public static void interProcessMutexTest(){
        CuratorFramework curatorFramework = curatorConnection.getInstance();
        final InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/a");
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
     * 如果少调用一次release，则此线程依然拥有锁。 上面的代码没有问题，我们可以多次调用acquire，后续的acquire也不会阻塞。
     * 将上面的InterProcessMutex换成不可重入锁
     */
    public static void interProcessSemaphoreMutexTest(){
        CuratorFramework curatorFramework = curatorConnection.getInstance();
        final InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(curatorFramework, "/a");
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


    /**
     * 类似JDK的ReentrantReadWriteLock. 一个读写锁管理一对相关的锁。
     * 一个负责读操作，另外一个负责写操作。 读操作在写锁没被使用时可同时由多个进程使用，而写锁使用时不允许读 (阻塞)。
     * 此锁是可重入的。一个拥有写锁的线程可重入读锁，但是读锁却不能进入写锁。 这也意味着写锁可以降级成读锁， 比如请求写锁 —>读锁 —->释放写锁。
     * 从读锁升级成写锁是不行的。
     */
    public static void interProcessReadWriteLockTest(){
        CuratorFramework curatorFramework = curatorConnection.getInstance();
        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(curatorFramework, "/a");
        // 读锁
        final InterProcessMutex readLock = readWriteLock.readLock();
        // 写锁
        final InterProcessMutex writeLock = readWriteLock.writeLock();

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

    /**
     * 信号量
     *
     *  一个计数的信号量类似JDK的Semaphore。 JDK中Semaphore维护的一组许可(permits)，而Cubator中称之为租约(Lease)。
     *  有两种方式可以决定semaphore的最大租约数。第一种方式是有用户给定的path决定。第二种方式使用SharedCountReader类。
     *  如果不使用SharedCountReader, 没有内部代码检查进程是否假定有10个租约而进程B假定有20个租约。
     *  所以所有的实例必须使用相同的numberOfLeases值.
     */
    public static void interProcessSemaphoreV2Test(){
        CuratorFramework curatorFramework = curatorConnection.getInstance();
        final InterProcessSemaphoreV2 semaphoreV2 = new InterProcessSemaphoreV2(curatorFramework, "/a", 2);
        for (int i = 0; i < 10; i++){
            final int  a = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Lease acquire = null;
                    try {
                        acquire = semaphoreV2.acquire();
                        Thread.sleep(5000);
                        System.out.println("这是第"+ a +"个的操作");
                        semaphoreV2.returnLease(acquire);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 分布式计数器
     */
    public static void distributedAtomicIntegerTest()  {
        CuratorFramework curatorFramework = curatorConnection.getInstance();
        RetryNTimes retryNTimes = new RetryNTimes(10,1000);
        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(curatorFramework, "/b", retryNTimes);
        AtomicValue<Integer> atomicValue = null;
        try {
            atomicValue = atomicInteger.add(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 是否成功
        System.out.println(atomicValue.succeeded());
        // 现在的值
        System.out.println(atomicValue.preValue());
        // 之前的值
        System.out.println(atomicValue.postValue());
    }

    public static void distributedBarrierTest(){
        CuratorFramework curatorFramework = curatorConnection.getInstance();
        final DistributedBarrier distributedBarrier = new DistributedBarrier(curatorFramework, "/a");
        try {
            distributedBarrier.setBarrier();
            for (int i = 0; i < 10; i++){
                final int a = i;
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("这是第"+ a + "个线程在等待了");
                            distributedBarrier.waitOnBarrier();
                            System.out.println("这是第"+ a + "个线程在释放了");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            Thread.sleep(5000);
            distributedBarrier.removeBarrier();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        // 可重入锁测试
        //interProcessMutexTest();

        // 不可重入锁测试
        //interProcessSemaphoreMutexTest();

        // 读些锁
        //interProcessReadWriteLockTest();

        // 信号量
        //interProcessSemaphoreV2Test();

        // 分布式计数器
        // distributedAtomicIntegerTest();

        // 分布式栏栅
        distributedBarrierTest();
    }
}
