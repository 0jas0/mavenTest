package com.jas.memory;

/**
 *  -Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 *  -Xmn 设置新生代的大小
 *  -XX:SurvivorRatio 设置Edan区占新生代的比例
 */
public class testAllocation {
    private final static int ONE_M = 1024*1024;
    public static void main(String[] args) {
        byte[] a1,a2,a3,a4;
        a1 = new byte[2*ONE_M];
        a2 = new byte[2*ONE_M];
        a3 = new byte[2*ONE_M];
        a4 = new byte[4*ONE_M];
    }
}
