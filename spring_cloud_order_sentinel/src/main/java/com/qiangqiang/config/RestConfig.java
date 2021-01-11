package com.qiangqiang.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.qiangqiang.exception.ExceptionUtils;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    @Bean
//    @LoadBalanced
//    @SentinelRestTemplate(blockHandler = "handleBlock",blockHandlerClass = ExceptionUtils.class,
//            fallback = "fallback",fallbackClass = ExceptionUtils.class)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
