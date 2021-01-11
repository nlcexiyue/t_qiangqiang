package com.qiangqiang;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/3
 * \* Time: 11:20
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
public class RabbitmqConsumer {

    @Value("${rabbitmq.queue}")
    public String queueName;

    public static int a = 0;

    /**
     * 自动创建队列，并关联routing key ,绑定交换机
     *
     * @RabbitListener 意思是当消息队列发生变化，消息事件产生了或者生产者发送消息了，马上就会触发这个方法，进行消息的消费
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("queue002"),
            key = "fruit",
            exchange = @Exchange("change1")
    ))
    public void consumer001(String msg) {
        System.out.println("消费者===>消费<===消息message: " + msg);

    }

//    @RabbitListener(queues = "queue002")
//    public void consumer002(String msg) {
//        System.out.println("消费者===>消费<===消息message: " + msg);
//
//    }

    /**
     * 自动创建queue002队列
     * @param msg
     */
    @RabbitListener(queuesToDeclare = @Queue("queue002"))
    public void consumer002(String msg) {
        System.out.println("消费者===>消费<===消息message: " + msg);

    }




    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("queue002"),
            exchange = @Exchange(value = "headers-exchange", type = ExchangeTypes.HEADERS),
            arguments = {
                    @Argument(name = "key-one", value = "1"),
                    @Argument(name = "key-two", value = "2"),
                    @Argument(name = "x-match", value = "any")
            }))
    public void consumer003(String msg) {
        System.out.println("消费者===>消费<===消息message: " + msg);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("queue004"),
            exchange = @Exchange(value = "headers-exchange", type = ExchangeTypes.HEADERS),
            arguments = {
                    @Argument(name = "key-one", value = "1"),
                    @Argument(name = "key-two", value = "2"),
                    @Argument(name = "x-match", value = "any")
            }))
    public void consumer004(String msg) {
        System.out.println("消费者===>消费<===消息message: " + msg);
    }



    //消息手动应答
    @RabbitListener(queuesToDeclare = @Queue("queue003"))
    public void revice(Message message , Channel channel) throws IOException {
        //设置Qos机制
        //第一个参数:单条消息的大小0表示无限制
        //第二个参数:每次处理消息的数量
        //第三个参数:是否为consumer级别,()false表示仅当前channel有效
        channel.basicQos(0,1,false);
        //手动应答消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);


    }
}