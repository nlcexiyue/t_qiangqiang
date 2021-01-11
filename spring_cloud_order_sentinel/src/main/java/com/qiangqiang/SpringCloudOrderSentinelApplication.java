package com.qiangqiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudOrderSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudOrderSentinelApplication.class, args);
    }

}
