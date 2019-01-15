package com.jas.concurrent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class FIFOMutex {
    // 用来保存获取锁的线程
    private Queue<Thread> concurrentQueue = new ConcurrentLinkedQueue<>();

    // 是否已经上锁了
    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public void lock(){
        Thread currentThread = Thread.currentThread();
        concurrentQueue.add(currentThread);
        // 队列的首元素不是当前队列， 或者上锁失败。那么阻塞该线程
        while (concurrentQueue.peek() != currentThread || !atomicBoolean.compareAndSet(false, true)){
            LockSupport.park(this);
        }
        // 获取锁成功，没有被阻塞。从队列中删除该线程
        concurrentQueue.remove(currentThread);
    }

    public void unlock(){
        atomicBoolean.set(false);
        LockSupport.unpark(concurrentQueue.peek());
    }

    public static void main(String[] args) {
        FIFOMutex lock = new FIFOMutex();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("线程1开始上锁了");
                    Thread.sleep(1000);
                    System.out.println("线程1释放锁了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("线程2开始上锁了");
                    Thread.sleep(1000);
                    System.out.println("线程2释放锁了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

            }
        });
        thread1.start();
        thread2.start();
    }

}
