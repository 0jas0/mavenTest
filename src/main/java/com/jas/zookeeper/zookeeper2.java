package com.jas.zookeeper;

import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class zookeeper2 implements Watcher{
    public static String connectionString = "127.0.0.1:2181";
    public static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(connectionString, 1000 * 1, new zookeeper2());
            countDownLatch.await();
            /*String s = zooKeeper.create("/node3", "value".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            Thread.sleep(2000);
            System.out.println(s);*/
            Stat stat = zooKeeper.setData("/node3", "aa".getBytes(), -1);
            Thread.sleep(2000);
            System.out.println(stat);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.getPath()+"----"+watchedEvent.getType()+"----"+watchedEvent.getState());
        // 创建链接的过程是异步的所以这里要加一个同步措施
        if (StringUtils.isEmpty(watchedEvent.getPath()) && watchedEvent.getType() == Event.EventType.None){
            if (watchedEvent.getState() == Event.KeeperState.SyncConnected){
                countDownLatch.countDown();
            }
        }else if (watchedEvent.getType() == Event.EventType.NodeCreated){
            System.out.println("创建节点触发的watcher");
        }else if (watchedEvent.getType() == Event.EventType.NodeDataChanged){
            System.out.println("数据发生改变");
        }

    }
}
