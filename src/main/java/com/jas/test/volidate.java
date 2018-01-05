package com.jas.test;

/**
 * Created by Administrator on 2017/11/29.
 */
public class volidate extends Thread {
    private static volatile int num = 0;
    public void add(){
        for(int i = 0;i<100;i++){
            num++;
        }
        System.out.println(num);
    }

    public void run() {
        add();
    }

    public static void main(String[] args) throws InterruptedException {
        volidate v1 = new volidate();
        volidate v2 = new volidate();
        volidate v3 = new volidate();
        volidate v4 = new volidate();
        volidate v5 = new volidate();
        volidate v6 = new volidate();
        v1.start();
        v2.start();
        v3.start();
        v4.start();
        v5.start();
        v6.start();
    }

}
