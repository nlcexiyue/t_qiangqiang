package com.qiangqiang.transcation;

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
public class QueueConsumer2 {
    private static final String ACTIVEMQ_URL = "tcp://192.168.200.108:61616";
//    private static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";

    public static void main(String[] args) throws JMSException {
        // 创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        // 打开连接
        connection.start();
        // 创建会话
        //第一个参数代表是否开启事务、如果开启，必须要提交才能到MQ里面去
        //第二个参数代表消息的签收方式
        //如果第一个参数为true，则第二个参数没什么意义
        //事务偏向生产者，签收偏向消费者，一般消费者不用开启事务，否则出现重复消费
        //事务的优先级大于签收的优先级
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        // 创建队列目标,并标识队列名称，消费者根据队列名称接收数据
        Destination destination = session.createQueue("myQueue");
        // 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        // 创建消费的监听
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;

                try {
                    System.out.println("消费的消息：" + textMessage.getText());
                    //消费者也要提交，才能把消费掉的消息从队列中移除
                    //如果一直不提交，重复消费7次之后，消息就进入到死信队列了
                    textMessage.acknowledge();
//                    if(textMessage.getText().equals("第5个文本消息")){
//                        System.out.println("回滚一次事务");
//                        session.rollback();
//                    }else{
//                        session.commit();
//                        System.out.println("提交一次事务");
//                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }

        });



    }
}