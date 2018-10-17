package com.jas.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

import java.util.List;

public class zkClient {
    public static void main(String[] args) throws InterruptedException {
        ZkConnection zkConnection = new ZkConnection(zookeeper1.connectionString);
        ZkClient zkClient = new ZkClient(zkConnection, 1000 * 1);

        //类似原生的 zookeeper的create方法
        //String aa = zkClient.create("/node", "aa", CreateMode.PERSISTENT);

        // 递归创建节点
        //zkClient.createPersistent("/a/aa/aaa",true);

        // 创建持久节点
        //zkClient.createPersistent("/a", "aa");

        // 创建临时节点
        //zkClient.createEphemeral("/a", "aa");

        // 删除节点
        //zkClient.delete("/a/aa/aaa");

        // 递归删除节点
        //zkClient.deleteRecursive("/a");

        // 获取和阅读子节点
        /*String parentDir = "/parent";
        List<String> childrens = zkClient.getChildren(parentDir);
        for (String children : childrens){
            String data = zkClient.readData(parentDir + "/" + children);
            System.out.println(data);
        }*/

        // 修改节点的值
        //zkClient.writeData("/parent", "modifyParent");

        /******************** watch的使用 ****************/


        // 1、监听子节点的变化 增加子节点 删除子节点
        zkClient.subscribeChildChanges("/parent", new IZkChildListener() {
            @Override
            public void handleChildChange(String parent, List<String> childrens) throws Exception {
                System.out.println("/parent"+"节点的子节点发生了变化");
                System.out.println("s的值是：" + parent + "下面的子节点为：" + childrens);
            }
        });

        //zkClient.createPersistent("/parent/children6", "children6");
        //Thread.sleep(1000);

        // 2、监听节点的值的变化
        zkClient.subscribeDataChanges("/parent", new IZkDataListener() {

            // 节点的数值变化会触发该方法
            @Override
            public void handleDataChange(String znode, Object value) throws Exception {
                System.out.println("节点为："+ znode + "，数值为" + value);
            }

            // 节点的删除会触发该方法
            @Override
            public void handleDataDeleted(String znode) throws Exception {
                System.out.println(znode);
            }
        });

        zkClient.writeData("/parent", "parent");
        Thread.sleep(1000);
    }
}
