package com.qiangqiang.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class KeyResolverConfiguration {
    //以下bean对象只能同时出现一个

    //根据uri限流
//    @Bean
    public KeyResolver pathKeyResolver(){
        //这一步就是拿到地址来做限流的
        return  exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }

    //根据参数限流
//    @Bean
    public KeyResolver parameterKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }

    //根据ip限流
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }


}
