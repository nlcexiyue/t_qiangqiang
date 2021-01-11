package com.qiangqaing.Producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import static com.qiangqaing.config.RabbitMQConfig.BUSINESS_EXCHANGE_NAME;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/11
 * \* Time: 13:29
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
public class BusinessMsgProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostConstruct
    private void init(){
        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setChannelTransacted(true);
    }

    @Transactional
    public void sendMsg(String msg){
        rabbitTemplate.convertAndSend(BUSINESS_EXCHANGE_NAME, "", msg);
//        rabbitTemplate.convertAndSend(BUSINESS_EXCHANGE_NAME, "key", msg);
        if (msg != null && msg.contains("exception")){
            throw new RuntimeException("surprise!");
        }
        System.out.println("消息已发送 {}"+msg);

    }



}