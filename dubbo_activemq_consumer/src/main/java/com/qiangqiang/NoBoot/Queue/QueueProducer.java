package com.qiangqiang.NoBoot.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/14
 * \* Time: 10:20
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class QueueProducer {
    private static final String ACTIVEMQ_URL = "tcp://192.168.200.108:61616";

    public static void main(String[] args) throws JMSException {
        // 创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        //开启异步投递
        activeMQConnectionFactory.setUseAsyncSend(true);
        // 创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        // 打开连接
        connection.start();
        // 创建会话 AUTO_ACKNOWLEDGE是自动签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建队列目标,并标识队列名称，消费者根据队列名称接收数据
        Destination destination = session.createQueue("myQueue");
        // 创建一个生产者
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(destination);
        //设置当前发动的所有消息的持久性，这里是对生产者进行持久
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 向队列推送10个文本消息数据
        for (int i = 1 ; i <= 10 ; i++){
            // 创建文本消息
            TextMessage message = session.createTextMessage("第" + i + "个文本消息");
            //消息持久
            try {
                //这里是对消息进行持久
                message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
                //这里是不持久
                message.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
                //设置消息过期时间
                message.setJMSExpiration(10000);
                //设置优先级，0-9级，0-4是普通级别，layui-9是加急级别，默认是4级
                message.setJMSPriority(9);
                //设置messageId
                message.setJMSMessageID("ssss");
            } catch (JMSException e) {
                e.printStackTrace();
            }

            //发送消息
            producer.send(message, new AsyncCallback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onException(JMSException exception) {

                }
            });
            //在本地打印消息
            System.out.println("已发送的消息：" + message.getText());
        }
        //关闭连接
        connection.close();
    }
}