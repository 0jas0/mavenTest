package com.jas.concurrent;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/12/5.
 */
public class MyQueue<T> {
    private LinkedList<T> list = new LinkedList<>();
    //最大容量
    private int maxSize;
    //最小容量
    private int minSize = 0;
    //计数
    private int cout = 0;

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void put(T t){
        if(cout==maxSize){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        cout++;
        System.out.println("向集合中添加了元素"+t);
        this.notify();
    }

    public synchronized void take(){
        if(cout==minSize){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = list.remove();
        System.out.println("从集合中取走了元素"+t);
        this.notify();
    }

    public static void main(String[] args) {
        final MyQueue<String> myQueue = new MyQueue<>(5);
        myQueue.put("a");
        myQueue.put("b");
        myQueue.put("c");
        myQueue.put("d");
        myQueue.put("e");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.put("f");
                myQueue.put("g");
            }
        },"t1");
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.take();
                myQueue.take();
            }
        },"t2");
        t2.start();
    }
}
