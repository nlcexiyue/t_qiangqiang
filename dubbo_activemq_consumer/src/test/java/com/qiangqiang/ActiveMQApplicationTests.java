package com.qiangqiang;


import org.apache.activemq.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.jms.*;

@SpringBootTest
class ActiveMQApplicationTests {

    @Autowired
    private JmsMessagingTemplate template;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @Autowired
    private ActiveMQConnectionFactory connectionFactory;


    @Test
    void contextLoads() throws JMSException {
        //设置重发规则
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(6);
        //给连接工程直接注入配置
        //如果是spring的配置，在broke的Factory中注入
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("queue");
        //开启延时投递后，10秒延时
//        textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,10000);
        //这是cron表达式，优先级最高，与其他的延时参数是重复叠加的，如果使用cron表达式，只需要加上repeat重复投递次数即可
        //用cron表达式，可以定时投递
//        textMessage.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON,"0 * * * *");
        producer.send(textMessage, new AsyncCallback() {
            @Override
            public void onSuccess() {
                System.out.println("chenggong "+textMessage);
            }

            @Override
            public void onException(JMSException exception) {
                System.out.println("消息出错了");
            }
        });

    }

}
