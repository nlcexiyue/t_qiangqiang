package com.qiangqiang.EmbedBroker;

import org.apache.activemq.broker.BrokerService;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/15
 * \* Time: 11:29
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class EmbedBroker {
    //创建一个activemq的broker实例
    public static void main(String[] args) {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        try {
            brokerService.addConnector("tcp://127.0.0.1:61616");
            brokerService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}