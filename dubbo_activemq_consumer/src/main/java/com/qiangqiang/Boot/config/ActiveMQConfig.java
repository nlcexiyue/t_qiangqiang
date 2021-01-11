package com.qiangqiang.Boot.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJcaListenerContainerFactory;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/14
 * \* Time: 11:41
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Configuration
public class ActiveMQConfig {
    @Value("${queueName}")
    private String queueName;

    @Value("${topicName}")
    private String topicName;

    @Value("${elasticSearchQueueName}")
    public String elasticSearchQueueName;

    @Value("${queueJsoupNewsName}")
    private String queueJsoupNewsName;

    @Value("${spring.activemq.user}")
    private String usrName;

    @Value("${spring.activemq.password}")
    private  String password;

    @Value("${spring.activemq.broker-url}")
    private  String brokerUrl;

    //要是开了集群，那么url是这样的
    private String clusterUrl = "failover:(tcp:127.0.0.1:61616,tcp:127.0.0.1:61716)";


    //开启异步投递的一种方式，加上jms.useAsyncSend=true就可以
    private String asyncUrl = "failover:(tcp:127.0.0.1:61616,tcp:127.0.0.1:61716)?jms.useAsyncSend=true";

    //初始化队列
    @Bean
    public Queue queue(){
        return new ActiveMQQueue(queueName);
    }


    //初始化主题
    @Bean
    public Topic topic(){
        return new ActiveMQTopic(topicName);
    }

    @Bean
    public Queue newsQueue(){
        return new ActiveMQQueue(queueJsoupNewsName);
    }


    //这是消息重试的配置对象，set还有其他属性，这个对象可以直接给工厂注入使用
    @Bean
    public RedeliveryPolicy redeliveryPolicy (){
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(6);
        return redeliveryPolicy;
    }


    //初始化连接工行
    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(usrName, password, brokerUrl);
        //这里开启的是异步投递，消息不阻塞，生产者会认为所有send的消息都被成功发送到MQ了，此时如果MQ突然宕机，生产者端内存中尚未发送至MQ的消息就会丢失
        //所以正确的异步发送方法是需要接受回调函数的
        activeMQConnectionFactory.setUseAsyncSend(true);

        return activeMQConnectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactoryQueue(ActiveMQConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        return defaultJmsListenerContainerFactory;
    }

    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactoryTopic(ActiveMQConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        //更改为topic模式
        defaultJmsListenerContainerFactory.setPubSubDomain(true);
        defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        return defaultJmsListenerContainerFactory;
    }










}