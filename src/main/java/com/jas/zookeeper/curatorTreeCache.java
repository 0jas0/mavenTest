package com.jas.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.zookeeper.CreateMode;

public class curatorTreeCache {
    public static void main(String[] args) throws Exception {
        // 3、可以监听本身节点的变化和子节点的变化
        CuratorFramework curatorFramework = curatorConnection.getInstance();
        TreeCache treeCache = new TreeCache(curatorFramework, "/b");
        treeCache.start();
        //
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                ChildData data = event.getData();
                switch (event.getType()){
                    case NODE_ADDED:
                        System.out.println("类型" + event.getType()
                                + "，节点的数据:" + new String(data.getData())
                                + "，节点的路径:" + data.getPath()
                                + "，节点的状态" + data.getStat());
                        break;
                    case NODE_UPDATED:
                        System.out.println("类型" + event.getType()
                                + "，节点的数据:" + new String(data.getData())
                                + "，节点的路径:" + data.getPath()
                                + "，节点的状态" + data.getStat());
                        break;
                    case NODE_REMOVED:
                        System.out.println("类型" + event.getType()
                                + "，节点的数据:" + new String(data.getData())
                                + "，节点的路径:" + data.getPath()
                                + "，节点的状态" + data.getStat());
                        break;
                    default:
                        break;
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
