package com.qiangqaing.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/11
 * \* Time: 13:20
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Configuration
public class RabbitMQConfig {
    public static final String BUSINESS_EXCHANGE_NAME = "rabbitmq.tx.demo.simple.business.exchange";
    public static final String BUSINESS_QUEUEA_NAME = "rabbitmq.tx.demo.simple.business.queue";

    // 声明业务Exchange
    @Bean("businessExchange")
    public FanoutExchange businessExchange(){
        return new FanoutExchange(BUSINESS_EXCHANGE_NAME);
    }

    // 声明业务队列
    @Bean("businessQueue")
    public Queue businessQueue(){
        return new Queue(BUSINESS_QUEUEA_NAME);
//        return QueueBuilder.durable(BUSINESS_QUEUEA_NAME).build();
    }

    // 声明业务队列绑定关系
    @Bean
    public Binding businessBinding(@Qualifier("businessQueue") Queue queue,
                                   @Qualifier("businessExchange") FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }
//    配置启动rabbitmq事务
    @Bean
    public RabbitTransactionManager rabbitTransactionManager(CachingConnectionFactory cachingConnectionFactory){
        return new RabbitTransactionManager(cachingConnectionFactory);
    }


}