package com.qiangqiang.GatewayConfig;

import com.qiangqiang.filter.CustomGatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;


import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/13
 * \* Time: 17:30
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \    网关路由配置类，可以注册自定义过滤器
 */
@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes().route(r -> r
                .path("/product/**")
                .uri("lb://product-server")
                .filters(new CustomGatewayFilter())
                .id("product-service"))
                .build();


    }
}