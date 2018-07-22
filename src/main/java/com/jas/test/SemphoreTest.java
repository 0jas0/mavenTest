package com.jas.test;

import java.util.concurrent.Semaphore;

public class SemphoreTest {
    public static void main(String[] args) {
        final Semaphore semphore = new Semaphore(0);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semphore.acquire();
                    System.out.println("aaa");
                    semphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
