package com.jas.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class zookeeper1 {
    public static String connectionString = "127.0.0.1:2181";
    public static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(connectionString, 1000 * 1, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    // 创建链接的过程是异步的所以这里要加一个同步措施
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected){
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            System.out.println(zooKeeper.getState());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
