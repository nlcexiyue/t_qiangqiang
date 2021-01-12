package com.qiangqiang.Fallback;

import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.BlockResponse;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackProvider;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class MyBlockFallbackProvider implements ZuulBlockFallbackProvider {
    @Override
    public String getRoute() {
        return "*"; // 服务名称
    }

    @Override
    public BlockResponse fallbackResponse(String route, Throwable throwable) {
        if (throwable instanceof BlockException) {
            return new BlockResponse(429, "服务访问压力过大，请稍后再试。", route);
        } else {
            return new BlockResponse(500, "系统错误，请联系管理员。", route);
        }
    }
}
