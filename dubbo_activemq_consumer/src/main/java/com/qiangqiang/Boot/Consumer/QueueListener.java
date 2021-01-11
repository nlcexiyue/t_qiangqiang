package com.qiangqiang.Boot.Consumer;

import com.alibaba.fastjson.JSON;
import com.qiangqiang.entity.NewsLibrary;
import com.qiangqiang.service.NewsLibiaryService;

import com.qiangqiang.tool.SnowFlakeId;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.qiangqiang.Boot.controller.PublishController.division;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/14
 * \* Time: 13:52
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
public class QueueListener {
    //远程引用的时候这里有启动时检查
    //远程引用的本地存根stub，在远程调用出错的情况下，直接执行本地的NewsLibraryServiceImpl1中的方法来获取默认值
    @Reference(timeout = 60000 ,
            check = true ,
            retries = 1,
            version = "*" ,
            stub = "com.qiangqiang.Boot.service.NewsLibraryServiceImpl1")
    private NewsLibiaryService newsLibiaryService;

//    @Autowired
//    private NewsLibraryService newsLibiaryService1;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * 监听新闻队列中的消息
     *
     */
    @JmsListener(destination = "news.queue",containerFactory = "jmsListenerContainerFactoryQueue")
    //这个是回调队列，相当于把接收到的消息再发到一个队列中，可以不写
//    @SendTo("out1.queue")
    //fallbackMethod为一个降级方法，参数都要和方法一样
//    @HystrixCommand(fallbackMethod = "")
    public void insertNewsLibrary(String text){
        String[] split = text.split(division);
        long id = SnowFlakeId.generateId();
        LocalDateTime parse = LocalDateTime.parse(split[2], DATE_TIME_FORMATTER);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = parse.atZone(zone).toInstant();
        Date date = Date.from(instant);
        int insert = newsLibiaryService.insert(id, split[0], split[1], date);
        System.out.println("");
    }


    @JmsListener(destination = "elasticSearch.queue",containerFactory = "jmsListenerContainerFactoryQueue")
    //这个是回调队列，相当于把接收到的消息再发到一个队列中，可以不写
//    @SendTo("out1.queue")
    //fallbackMethod为一个降级方法，参数都要和方法一样
//    @HystrixCommand(fallbackMethod = "")
    public void insertToElasticSearchNewsLibrary(String text){
        NewsLibrary newsLibrary = JSON.parseObject(text, NewsLibrary.class);
//        newsLibiaryService1.save(newsLibrary);

    }




    //destination监听的目标
    @JmsListener(destination = "publish.queue",containerFactory = "jmsListenerContainerFactoryQueue")
    @SendTo("out.queue")
    //sendTo会将此方法返回的数据，写入到queue:out.queue中去
    public void receive(String text){
        System.out.println("QueueListener: consumer-a 收到一条信息: " + text);

    }


}