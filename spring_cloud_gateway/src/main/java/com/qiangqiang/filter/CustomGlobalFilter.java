package com.qiangqiang.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/13
 * \* Time: 17:49
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \    自定义全局顾虑器，用过统一鉴权，在oauth2协议中，token的鉴权可以在自定义全局过滤器中来做
 */
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 过滤器业务逻辑，统一鉴权
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //可以通过exchange获取header头信息的协议token
//        String token = exchange.getRequest().getHeaders().getFirst("token");
//        System.out.println("获取请求头中的令牌：" + token);
//        return chain.filter(exchange);
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        // 业务逻辑处理
        if (null == token) {
            ServerHttpResponse response = exchange.getResponse();
            // 响应类型
            response.getHeaders().add("Content-Type", "application/json; charset=utf-8");
            // 响应状态码，HTTP 401 错误代表用户没有访问权限
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 响应内容
            String message = "{\"message\":\"" + HttpStatus.UNAUTHORIZED.getReasonPhrase() + "\"}";
            DataBuffer buffer = response.bufferFactory().wrap(message.getBytes());
            // 请求结束，不在继续向下请求
            return response.writeWith(Mono.just(buffer));
        }
        // 使用 token 进行身份验证

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}