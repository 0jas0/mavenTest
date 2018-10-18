package com.jas.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;

public class curatorNodeCacheListen {
    public static void main(String[] args) throws Exception {
        // 1、NodeCache：监听本身节点的新增、修改操作。
        CuratorFramework curatorFramework = curatorConnection.getInstance();

        final NodeCache nodeCache = new NodeCache(curatorFramework, "/b");
        nodeCache.start(true);
        // 添加监控
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                ChildData currentData = nodeCache.getCurrentData();
                if (currentData != null) {
                    System.out.println("data:" + new String(currentData.getData()));
                    System.out.println("path:" + currentData.getPath());
                    System.out.println("stat:" + currentData.getStat());
                }
            }
        });

        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
            }
        }).forPath("/b","b".getBytes());

        curatorFramework.setData().forPath("/b", "c".getBytes());

        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath("/b");

        Thread.sleep(2000);

    }
}
