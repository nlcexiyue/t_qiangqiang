package com.qiangqiang;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/3
 * \* Time: 11:09
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
public class RabbitmqProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.queue}")
    public String queueName;

    /**
     * direct直连发送消息
     */
    public void directProducer(String msg){

        this.amqpTemplate.convertAndSend(queueName,msg);
    }

    /**
     * fanout或者topic发送消息
     */
    public void exchangeProducer(String exchange , String routingKey, String msg){
        this.amqpTemplate.convertAndSend(exchange,routingKey,msg);
    }

    /**
     * header发送消息
     */
    public void exchangeProducer(String exchange, String msg ){


        amqpTemplate.convertAndSend(exchange, "", msg, message -> {
            MessageProperties properties = message.getMessageProperties();
            properties.setHeader("key-one", "1");
            return message;
        });
    }







}