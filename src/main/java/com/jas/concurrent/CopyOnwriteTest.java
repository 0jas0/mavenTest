package com.jas.concurrent;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 测试 CopyOnWriteTest 迭代器的弱一致性
 */
public class CopyOnwriteTest {
    public static void main(String[] args) throws InterruptedException {
        final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("hello");
        list.add("alibaba");
        list.add("world");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                list.add("哈喽!!!");
                list.add("树先生");
            }
        });
        Iterator<String> iterator = list.iterator();
        thread.start();
        thread.join();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("****************分割线***************");
        for (String item : list){
            System.out.println(item);
        }
    }
}
