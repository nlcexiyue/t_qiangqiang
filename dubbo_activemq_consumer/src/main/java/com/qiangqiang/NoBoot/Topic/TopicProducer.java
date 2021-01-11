package com.qiangqiang.NoBoot.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/14
 * \* Time: 10:37
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class TopicProducer {
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
        Destination destination = session.createTopic("topicTest");
        // 创建一个生产者
        MessageProducer producer = session.createProducer(destination);
        //topic模式是不能持久化的
        //非持久的topic没有意义，因为发布订阅是先启动订阅再启动生产，消息已经被消费了，如果先启动生产者后启动订阅，消息会被当做废消息
        // 向队列推送10个文本消息数据
        for (int i = 1 ; i <= 10 ; i++){
            // 创建文本消息
            TextMessage message = session.createTextMessage("第" + i + "个文本消息");
            //发送消息
            producer.send(message);
            //在本地打印消息
            System.out.println("已发送的消息：" + message.getText());
        }
        //关闭连接
        connection.close();
    }
}