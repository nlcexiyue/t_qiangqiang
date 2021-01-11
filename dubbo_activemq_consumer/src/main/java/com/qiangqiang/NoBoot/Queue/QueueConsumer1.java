package com.qiangqiang.NoBoot.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/14
 * \* Time: 10:30
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class QueueConsumer1 {
    private static final String ACTIVEMQ_URL = "tcp://192.168.200.108:61616";

    public static void main(String[] args) throws JMSException {
        // 创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        // 打开连接
        connection.start();
        // 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建队列目标,并标识队列名称，消费者根据队列名称接收数据
        Destination destination = session.createQueue("myQueue");
        // 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        // 创建消费的监听
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {


                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;

                    try {
                        System.out.println("消费的消息：" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}