package com.qiangqiang.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.netflix.discovery.converters.Auto;
import com.qiangqiang.entity.Order;
import com.qiangqiang.entity.Product;
import com.qiangqiang.service.OrderService;
import com.qiangqiang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;



    @Autowired
    private ProductService productService;


//    @SentinelResource(value = "getOrderById",
//            blockHandler = "blockGetOrderById",
//            fallback = "fallbackGetOrderById")
    @Override
    public Order getOrderById() {
        //feign声明式调用
//        Order order = new Order(1, "用户订单", 2, productService.getProduct());
        //RestTemplate远程调用
        Order order = new Order(1, "用户订单", 2, productService.getProduct());
        return order;
    }

    public Order blockGetOrderById(BlockException ex) {

        System.out.println("order-server-sentinel服务的getOrderById方法被限流了，异常信息为："+ex.toString());
        Order order = new Order(1, "用户订单被限流兜底数据", 2, null);
        return order;
    }

    public Order fallbackGetOrderById(Throwable throwable) {
        System.out.println("order-server-sentinel服务的getOrderById方法被熔断了，异常信息为："+throwable);
        Order order = new Order(1, "用户订单被熔断兜底数据", 2, null);
        return order;
    }



    //通过@LoadBalanced注解实现远程调用
    private List<Product> selectProductLoadBalanced() {

        //根据服务名称从注册中心获取服务
        String url = "http://product-server/product";

        ResponseEntity<List<Product>> exchange = restTemplate.exchange(
                url,        //访问路径
                HttpMethod.GET,     //请求方式
                null,   //请求参数
                new ParameterizedTypeReference<List<Product>>() {   //返回的类型
                });
        return exchange.getBody();

    }


}
