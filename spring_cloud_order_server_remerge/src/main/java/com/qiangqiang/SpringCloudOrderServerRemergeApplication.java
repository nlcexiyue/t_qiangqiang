package com.qiangqiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//熔断器注解，下面的二选一
@EnableHystrix
//@EnableCircuitBreaker
@EnableFeignClients
public class SpringCloudOrderServerRemergeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudOrderServerRemergeApplication.class, args);
    }

}
