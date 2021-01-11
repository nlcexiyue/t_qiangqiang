package com.qiangqiang.Zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/2
 * \* Time: 15:42
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ZookeeperDemo {

    private static ZkClient zkClient = null;

    private static final String SERVERSTRING = "192.168.200.111:2181";

    static{
        zkClient = new ZkClient(SERVERSTRING,1000,10000);
    }

    public static void main(String[] args) {
//        zkClient.create("/java","java", CreateMode.PERSISTENT);
        zkClient.writeData("/sanguo" , "111");

//        Object o = zkClient.readData("/java");
//        System.out.println(o);
//        zkClient.delete("/java");
        System.out.println("操作成功");



    }


}