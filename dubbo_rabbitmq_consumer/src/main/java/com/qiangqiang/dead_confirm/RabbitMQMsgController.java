package com.qiangqiang.dead_confirm;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.qiangqiang.dead_confirm.RabbitMQConfig.BUSINESS_EXCHANGE_NAME;

@RequestMapping("rabbitmq")
@RestController
public class RabbitMQMsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("sendmsg")
    public void sendMsg(String msg){
        rabbitTemplate.convertSendAndReceive(BUSINESS_EXCHANGE_NAME,"",msg);
    }
}