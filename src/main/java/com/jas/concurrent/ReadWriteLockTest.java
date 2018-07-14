package com.jas.concurrent;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2017/12/10.
 */
public class ReadWriteLockTest {
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();
    public void method1(){
        try {
            readLock.lock();
            System.out.println("进入method1方法");
            Thread.sleep(500);
            System.out.println("出method1方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }
    public void method2(){
        try {
            writeLock.lock();
            System.out.println("进入method2方法");
            Thread.sleep(500);
            System.out.println("出method2方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }
    public void method3(){
        try {
            writeLock.lock();
            System.out.println("进入method3方法");
            Thread.sleep(500);
            System.out.println("出method3方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }
    public void method4(){
        try {
            readLock.lock();
            System.out.println("进入method4方法");
            Thread.sleep(500);
            System.out.println("出method4方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLockTest.method1();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLockTest.method2();
            }
        },"t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLockTest.method3();
            }
        },"t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLockTest.method4();
            }
        },"t4");
        t1.start();
        t4.start();
        t2.start();
        /* t3.start();*/
    }
}
