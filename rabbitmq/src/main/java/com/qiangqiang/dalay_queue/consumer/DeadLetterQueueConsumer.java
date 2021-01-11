package com.qiangqiang.dalay_queue.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

import static com.qiangqiang.dalay_queue.config.RabbitMQConfig.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/11
 * \* Time: 10:20
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
public class DeadLetterQueueConsumer {

    int a = 0;
    @RabbitListener(queues = DELAY_QUEUEC_NAME)
    public void receive1(Message message, Channel channel) throws IOException {
        a++;
        String msg = new String(message.getBody());
        System.out.println(new Date() + "延时队列C收到消息：{"+msg+"}" );
        if(a < 3){
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false , true);
        }else{
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false , false);
        }

    }




    @RabbitListener(queues = DEAD_LETTER_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        System.out.println(new Date() + "死信队列A收到消息：{"+msg+"}" );
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
    @RabbitListener(queues = DEAD_LETTER_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        System.out.println(new Date() + "死信队列B收到消息：{"+msg+"}" );
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = DEAD_LETTER_QUEUEC_NAME)
    public void receiveC(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        System.out.println(new Date() + "死信队列C收到消息：{"+msg+"}" );
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}