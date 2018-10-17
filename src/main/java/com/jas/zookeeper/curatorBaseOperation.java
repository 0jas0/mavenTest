package com.jas.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class curatorBaseOperation {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = curatorConnection.getInstance();
        curatorFramework.start();

        /**
         *  创建节点，creatingParentsIfNeeded（）是如果父节点不存在，则在创建节点的同时创建父节点；
         *      withMode（）是创建的模式
         *      inBackground（）是回调函数
         */
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("Path:" + curatorEvent.getPath());
                System.out.println("Type:" + curatorEvent.getType());
            }
        }).forPath("/a", "aa".getBytes());

        curatorFramework.create().inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("Path:" + curatorEvent.getPath());
                System.out.println("Type:" + curatorEvent.getType());
            }
        }).forPath("/a/aa","aaaa".getBytes());

        // 获取节点的值
        byte[] bytes = curatorFramework.getData().forPath("/a");
        System.out.println(new String(bytes));

        // 判断节点是否存在
        Stat stat = curatorFramework.checkExists().forPath("/a");
        System.out.println(stat);

        // 更新节点的值
        curatorFramework.setData().forPath("/a", "aaa".getBytes());
        System.out.println(new String(curatorFramework.getData().forPath("/a")));


        // 获取子节点
        List<String> pathList = curatorFramework.getChildren().forPath("/a");
        for (String path : pathList){
            System.out.println("znode:" +path);
            System.out.println("value:"+ new String(curatorFramework.getData().forPath("/a/"+path)));
        }

        // 删除节点
        // guaranteed 安全的删除节点
        // deletingChildrenIfNeeded 删除节点如果存在子节点
        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath("/a");

    }
}
