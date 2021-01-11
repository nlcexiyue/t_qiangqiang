package com.qiangqiang.dalay_queue.controller;

import com.qiangqiang.dalay_queue.producer.DelayMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/11
 * \* Time: 10:29
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RequestMapping("rabbitmq")
@RestController
public class RabbitMQMsgController {
    @Autowired
    private DelayMessageSender sender;

    @RequestMapping("sendmsg")
    public void sendMsg(String msg, Integer delayType){
        System.out.println(new Date() + "发送请求，msg:" + msg + "延时时间:"+delayType);
        sender.sendMsg(msg, delayType);
    }
}