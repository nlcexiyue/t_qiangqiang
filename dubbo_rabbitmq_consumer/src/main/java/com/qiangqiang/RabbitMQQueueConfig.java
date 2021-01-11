package com.qiangqiang;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/3
 * \* Time: 10:53
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Configuration
public class RabbitMQQueueConfig {
    @Value("${rabbitmq.queue}")
    private String queueName;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }


    private static final String topicExchangeName = "topic-exchange";
    private static final String fanoutExchangeName = "fanout-exchange";
    private static final String headersExchangeName = "headers-exchange";
    //声明队列
//    @Bean
//    public Queue createQueue(){
//        return new Queue("queue001", false, true, true);
//    }



//    //声明Topic交换机
//    @Bean
//    public TopicExchange topicExchange(){
//        return new TopicExchange(topicExchangeName);
//    }
//
//
//    /**
//     * 将队列与Topic交换机进行绑定,并指定路由键,进行模糊匹配
//     * @param queue
//     * @param topicExchange
//     * @return
//     */
//    @Bean
//    public Binding topicBinging(Queue queue , TopicExchange topicExchange){
//        System.out.println("Topic队列中" + queue);
//        return BindingBuilder.bind(queue).to(topicExchange).with("org.#");
//    }
//
//
//
//    /**
//     * 声明fanout交换机
//     */
//    @Bean
//    public FanoutExchange fanoutExchange(){
//        return new FanoutExchange(fanoutExchangeName);
//    }
//
//    /**
//     * 将队列与fanout交换机绑定
//     */
//    @Bean
//    public Binding fanoutBinding(Queue queue,FanoutExchange fanoutExchange){
//        System.out.println("fanout队列中" + queue);
//        return BindingBuilder.bind(queue).to(fanoutExchange);
//    }
//
//
//
//    /**
//     * 声明header
//     */
//    @Bean
//    public HeadersExchange headersExchange(){
//        return new HeadersExchange(headersExchangeName);
//    }
//
//    /**
//     * 将队列与header交换机进行绑定
//     */
//    @Bean
//    public Binding headerBinding(Queue queue,HeadersExchange headersExchange){
//        Map<String, Object> map = new HashMap<>();
//        map.put("First","A");
//        map.put("Fourth","D");
//        return BindingBuilder.bind(queue).to(headersExchange).whereAny(map).match();
//    }


}