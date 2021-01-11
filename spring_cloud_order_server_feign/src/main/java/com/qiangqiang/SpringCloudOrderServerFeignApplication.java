package com.qiangqiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudOrderServerFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudOrderServerFeignApplication.class, args);
    }

}
