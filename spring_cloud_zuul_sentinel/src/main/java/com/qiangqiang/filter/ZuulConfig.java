package com.qiangqiang.filter;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulErrorFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPostFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPreFilter;
import com.netflix.zuul.ZuulFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

//如果使用sentinel-zuul-adapter，才将zuul的三个过滤器重写并注入
@Configuration
public class ZuulConfig {


    @Bean
    public ZuulFilter sentinelZuulPreFilter() {
        return new SentinelZuulPreFilter();
    }

    @Bean
    public ZuulFilter sentinelZuulPostFilter() {
        return new SentinelZuulPostFilter();
    }

    @Bean
    public ZuulFilter sentinelZuulErrorFilter() {
        return new SentinelZuulErrorFilter();
    }

    @PostConstruct
    public void doInit() {
        //加载网关限流规则
        initGatewayRules();
    }

    /**
     * 网关限流规则
     */
    private void initGatewayRules() {
        //需要一个HashSet
        Set<GatewayFlowRule> rule = new HashSet<>();
        /**
         * resource:资源名称，可以是网关中的route名称或者用户自定义的API分组名称
         * count：限流阈值
         * intervalSec：统计时间窗口
         */
        rule.add(new GatewayFlowRule("product-server")
                .setCount(5)            //限流阈值
                .setIntervalSec(60));   //统计时间窗口，单位是秒
        //加载网关限流规则
        GatewayRuleManager.loadRules(rule);


    }
}
