package com.jas.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;

public class curatorPathChildrenCacheListen {
    public static void main(String[] args) throws Exception {
        // 2、NodeCache：监听子节点的新增、修改等操作。
        CuratorFramework curatorFramework = curatorConnection.getInstance();

        final PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, "/a", true);
        /**
         * 如果不填写这个参数，则无法监听到子节点的数据更新
         如果参数为PathChildrenCache.StartMode.BUILD_INITIAL_CACHE，则会预先创建之前指定的/super节点
         如果参数为PathChildrenCache.StartMode.POST_INITIALIZED_EVENT，效果与BUILD_INITIAL_CACHE相同，只是不会预先创建/super节点
         参数为PathChildrenCache.StartMode.NORMAL时，与不填写参数是同样的效果，不会监听子节点的数据更新操作
         */
        pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        // 添加监控
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                PathChildrenCacheEvent.Type type = event.getType();
                switch (type){
                    case CHILD_ADDED:
                        System.out.println("类型：" + event.getType() + "，数据：" + event.getData());break;
                    case CHILD_UPDATED:
                        System.out.println("类型：" + event.getType() + "，数据：" + event.getData());break;
                    case CHILD_REMOVED:
                        System.out.println("类型：" + event.getType() + "，数据：" + event.getData());break;
                    default:
                        break;
                }
            }
        });

        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
            }
        }).forPath("/a/b","b".getBytes());

        curatorFramework.setData().forPath("/a/b", "c".getBytes());

        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath("/a/b");

        Thread.sleep(2000);

    }
}
