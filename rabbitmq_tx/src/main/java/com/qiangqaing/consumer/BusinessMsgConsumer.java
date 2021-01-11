package com.qiangqaing.consumer;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.qiangqaing.config.RabbitMQConfig.BUSINESS_EXCHANGE_NAME;
import static com.qiangqaing.config.RabbitMQConfig.BUSINESS_QUEUEA_NAME;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/11
 * \* Time: 13:23
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
public class BusinessMsgConsumer {
    @RabbitListener(queues = BUSINESS_QUEUEA_NAME)
    public void receiveMsg(Message message, Channel channel) throws IOException {
        channel.txSelect();
        channel.txCommit();
//        channel.basicPublish(BUSINESS_EXCHANGE_NAME,"", MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBody());
        String msg = new String(message.getBody());
        System.out.println("收到业务消息：{}"+ msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}