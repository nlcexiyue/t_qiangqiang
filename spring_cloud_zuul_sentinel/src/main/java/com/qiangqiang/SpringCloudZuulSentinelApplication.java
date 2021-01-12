package com.qiangqiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SpringCloudZuulSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZuulSentinelApplication.class, args);
    }

}
