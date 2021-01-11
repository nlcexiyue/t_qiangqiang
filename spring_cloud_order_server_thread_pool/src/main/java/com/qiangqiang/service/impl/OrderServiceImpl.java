package com.qiangqiang.service.impl;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qiangqiang.entity.Order;
import com.qiangqiang.entity.Product;
import com.qiangqiang.service.OrderService;
import com.qiangqiang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @HystrixCommand(
            groupKey = "order-server-list", // 一组command，如果没有配threadPoolKey，相同的groupKey会使用同一线程池（线程池隔离策略下）
            commandKey = "getOrder", // 服务标识，默认就是方法名
            threadPoolKey = "order-server-list", // 可以在多个方法上定义同一个线程池
            commandProperties = {
                    // 超时时间
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "5000")
            },
            threadPoolProperties = {
                    // 线程池大小
                    @HystrixProperty(name = "coreSize", value = "7"),
                    //队列等待阈值
                    @HystrixProperty(name = "maxQueueSize", value = "100"),
                    //线程存活时间
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    //超出队列等待阈值执行拒绝策略
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "100")
            },fallbackMethod = "undertake1")        // 指定降级方法，在熔断和异常时会走降级方法
    @Override
    public Order getOrder(Integer id) {
        System.out.println(Thread.currentThread().getName()+"------------");
        Order order = new Order(1, "用户订单", 2, productService.getProductById(id));
        return order;
    }


    public Order undertake1(Integer id) {
        Order order = new Order(1, "用户订单兜底兜底", 2, productService.getProduct());
        return order;
    }

    @HystrixCommand(
            groupKey = "order-server-single", // 一组command，如果没有配threadPoolKey，相同的groupKey会使用同一线程池（线程池隔离策略下）
            commandKey = "getOrderById", // 服务标识，默认就是方法名
            threadPoolKey = "order-server-single", // 可以在多个方法上定义同一个线程池
            commandProperties = {
                    // 超时时间
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "5000")
            },
            threadPoolProperties = {
                    // 线程池大小
                    @HystrixProperty(name = "coreSize", value = "3"),
                    //队列等待阈值
                    @HystrixProperty(name = "maxQueueSize", value = "100"),
                    //线程存活时间
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    //超出队列等待阈值执行拒绝策略
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "100")
            },fallbackMethod = "undertake")        // 指定降级方法，在熔断和异常时会走降级方法
    @Override
    public Order getOrderById() {
        System.out.println(Thread.currentThread().getName()+"------------");
        Order order = new Order(1, "用户订单", 2, productService.getProduct());
        return order;
    }

    public Order undertake() {
        Order order = new Order(1, "用户订单兜底兜底", 2, productService.getProduct());
        return order;
    }


}
