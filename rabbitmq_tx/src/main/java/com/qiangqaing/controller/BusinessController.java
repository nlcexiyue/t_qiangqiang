package com.qiangqaing.controller;

import com.qiangqaing.Producer.BusinessMsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/11
 * \* Time: 13:37
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
public class BusinessController {
    @Autowired
    private BusinessMsgProducer producer;

    @RequestMapping("send")
    @Transactional
    public void sendMsg(String msg){
        System.out.println(msg);
        producer.sendMsg(msg);
    }
}