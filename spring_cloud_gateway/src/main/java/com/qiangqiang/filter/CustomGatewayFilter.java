package com.qiangqiang.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/13
 * \* Time: 17:24
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \自定义网关过滤器，实现GlobalFilter和Ordered接口
 */
public class CustomGatewayFilter implements GatewayFilter, Ordered {

    /**
     * 过滤器业务逻辑
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("自定义网关过滤器");
        return chain.filter(exchange);      //表示通过，继续向下执行
    }

    /**
     * 过滤器执行顺序，数值越小，优先度越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}