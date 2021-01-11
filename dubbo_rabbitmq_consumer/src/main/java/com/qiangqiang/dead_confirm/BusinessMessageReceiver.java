package com.qiangqiang.dead_confirm;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.qiangqiang.dead_confirm.RabbitMQConfig.BUSINESS_QUEUEA_NAME;
import static com.qiangqiang.dead_confirm.RabbitMQConfig.BUSINESS_QUEUEB_NAME;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/10
 * \* Time: 11:50
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
public class BusinessMessageReceiver {

    @RabbitListener(queues = BUSINESS_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException{
        String msg = new String(message.getBody());
        Boolean ack = true;

        try {
            if (msg.contains("deadletter")){
                throw new RuntimeException("dead letter exception");
            }
        } catch (RuntimeException e) {
            ack = false;
        }
        if(!ack){
            System.out.println("A消息消费发生异常，error msg:{}");
            //这里签收拒绝了.Nack可以一次拒绝接受多条消息,也可以设置是否扔回去原队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }else{
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }
    }

    @RabbitListener(queues = BUSINESS_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        System.out.println("收到业务消息B：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}