package com.qiangqiang.dalay_queue.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.qiangqiang.dalay_queue.config.RabbitMQConfig.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/11
 * \* Time: 10:24
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
public class DelayMessageSender implements RabbitTemplate.ConfirmCallback{
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostConstruct
    private void init(){
//        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.setMandatory(true);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (b) {
            System.out.println("消息确认成功, id:{}"+id);

        } else {
            System.out.println("消息未成功投递, id:{}"+id+"cause:{}"+ s);

        }
    }

    public void sendMsg(String msg, Integer type){

        if(type.equals(6000)){
            rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEA_ROUTING_KEY, msg);
        }else if(type.equals(10000)){
            rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEB_ROUTING_KEY, msg);
        }else if(type < 10000 &&  type > 6000){
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setExpiration(type.toString());
            Message message = new Message(msg.getBytes(), messageProperties);
            rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEC_ROUTING_KEY, message);
        }else{
            rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEC_ROUTING_KEY, msg);
        }
    }

}