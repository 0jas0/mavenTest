package com.jas.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/12/10.
 */
public class ReentrantLockTest {
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition1 = reentrantLock.newCondition();
    private Condition condition2 = reentrantLock.newCondition();
    public void method1(){
        try {
            reentrantLock.lock();
            System.out.println("进入method1方法");
            Thread.sleep(500);
            condition1.await();
            System.out.println("出method1方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
    public void method2(){
        try {
            reentrantLock.lock();
            System.out.println("进入method2方法");
            Thread.sleep(500);
            condition2.await();
            System.out.println("出method2方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
    public void method3(){
        try {
            reentrantLock.lock();
            System.out.println("进入method3方法");
            Thread.sleep(500);
            condition1.signal();
            System.out.println("出method3方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
    public void method4(){
        try {
            reentrantLock.lock();
            System.out.println("进入method4方法");
            Thread.sleep(500);
            condition2.signal();
            System.out.println("出method4方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockTest.method1();
            }
        },"t1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockTest.method2();
            }
        },"t2");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockTest.method3();
            }
        },"t3");
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockTest.method4();
            }
        },"t4");

        thread2.start();
        thread1.start();
        thread3.start();
        thread4.start();
    }
}
