package com.qiangqiang.Zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/2
 * \* Time: 15:57
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ZookeeperWatch {
    private static ZkClient zkClient = null;

    private static final String SERVERSTRING = "192.168.200.100:2181";

    static{
        zkClient = new ZkClient(SERVERSTRING,1000,10000,new CustomSerializer());
    }

    public static void main(String[] args) throws IOException {
        //监听节点值的变化
        zkClient.subscribeDataChanges("/sanguo", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("检测到值变更了："+s + "变为了：" + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("检测到值被删除了" + s);
            }

        });

        zkClient.subscribeChildChanges("/sanguo", new IZkChildListener() {
            /**
             *
             * @param s 父节点路径
             * @param list  所有当前父节点下的所有节点
             * @throws Exception
             */
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                String node = "";
                for (String s1 : list) {
                    node = node + "," + s1;
                }
                System.out.println("检测到 " + s + "路径下的节点 "  + node + "变化了");
            }
        });

        System.in.read();
    }



}