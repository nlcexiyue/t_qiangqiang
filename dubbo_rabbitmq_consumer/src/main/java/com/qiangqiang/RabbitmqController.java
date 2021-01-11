package com.qiangqiang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/3
 * \* Time: 11:08
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@Controller
public class RabbitmqController {

    @Resource
    private RabbitmqProducer rabbitmqProducer;

    @RequestMapping(value = "/sendMessage1")
    @ResponseBody
    public void rabbitmqSendMessage() {
        String msg = "消息产===>生者<===消息message: ";
        for (int i = 0; i < 10000; i++) {
            rabbitmqProducer.directProducer(msg+i);
        }
    }

    @RequestMapping(value = "/sendMessage2")
    @ResponseBody
    public void rabbitmqExchangeSendMessage() {
        String msg = "消息产===>生者<===消息message: ";
        for (int i = 0; i < 100000; i++) {
            rabbitmqProducer.exchangeProducer("topic-exchange","org.write.test",msg+i);
        }
    }

    @RequestMapping(value = "/sendMessage3")
    @ResponseBody
    public void rabbitmqHeaderSendMessage(){
        for (int i = 0; i < 1000; i++) {
            rabbitmqProducer.exchangeProducer("headers-exchange", "hello word");
        }
        System.out.println("发送成功");
    }

}