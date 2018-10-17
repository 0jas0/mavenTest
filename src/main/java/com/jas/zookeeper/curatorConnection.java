package com.jas.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class curatorConnection {

    private  static Object lock = new Object();

    private static CuratorFramework  curatorFramework = null;

    public static CuratorFramework getInstance(){
        if (curatorFramework == null) {
            synchronized (lock) {
                if (curatorFramework == null) {
                    // retryPolicy，重试连接策略，有四种实现，分别为：
                    // ExponentialBackoffRetry（重试指定的次数, 且每一次重试之间停顿的时间逐渐增加）
                    // RetryNtimes（指定最大重试次数的重试策略）
                    // RetryOneTimes（仅重试一次）
                    // RetryUntilElapsed（一直重试直到达到规定的时间）
                    ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 10);
                    curatorFramework = CuratorFrameworkFactory
                            // connectString，zookeeper服务器地址及端口号，多个zookeeper服务器地址以“,”分隔。
                            .builder().connectString(zookeeper1.connectionString)
                            // sessionTimeoutMs，会话超时时间，单位毫秒，默认为60000ms。
                            .sessionTimeoutMs(1000).connectionTimeoutMs(1000)
                            .retryPolicy(retry).build();
                }
            }
        }
        return curatorFramework;
    }

    public static void main(String[] args) {
        CuratorFramework curatorFramework = getInstance();
        // 开启连接
        curatorFramework.start();
        System.out.println("连接成功");
    }
}
