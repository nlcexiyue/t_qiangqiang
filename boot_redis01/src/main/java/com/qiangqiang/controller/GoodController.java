package com.qiangqiang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class GoodController {

    public static final String REDIS_LOCK = "LOCK[1]";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Value("${server.port}")
    public String serverPort;

    @GetMapping("buy_goods")
    public String buy_goods(){

        String s = UUID.randomUUID().toString().replaceAll("-", "");

        String value =  s;
        try {
            //加redis分布式锁
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value,10L, TimeUnit.SECONDS);

            if (!flag) {
                return "强锁失败";
            }
            //看看库存的数量够不够
            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodNumber = result == null ? 0 : Integer.parseInt(result);

            if (goodNumber > 0){
                int realNumber = goodNumber -1;
                stringRedisTemplate.opsForValue().set("goods:001",String.valueOf(realNumber));
                System.out.println("成功买到商品,还剩" + realNumber + "件" + "\t服务提供端口是" + serverPort);


                return "成功买到商品,还剩" + realNumber + "件" + "\t服务提供端口是" + serverPort;
            }else{
                System.out.println("商品已经售完\t服务提供端口是"+ serverPort);
                return "意外发生";
            }
        } finally {
            //解分布式锁
            stringRedisTemplate.delete(REDIS_LOCK);
        }

    }

}
