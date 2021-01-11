package com.qiangqiang.service.impl;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
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
import java.util.concurrent.Future;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;


    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProductService productService;


    @HystrixCollapser(batchMethod = "getOrderByIds",
            scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties = {
            //间隔多久的请求会进行合并，默认10ms
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "20"),
            //批处理之前，批处理中允许的最大请求数
            @HystrixProperty(name = "maxRequestsInBatch", value = "200"),
    })
    @Override
    public Future<Order> getOrder(Integer id) {
        System.out.println("单独的方法被单独调用了");
//        Order order = new Order(1, "用户订单", 2, productService.getProductById(id));
        return null;
    }

    //声明需要服务容错的方法
    @HystrixCommand
    @Override
    public List<Order> getOrderByIds(List<Integer> ids) {
        System.out.println("批量的请求方法被调用了");
        Order order1 = new Order(1, "用户订单", 2, productService.getProduct());
        Order order2 = new Order(1, "用户订单", 2, productService.getProduct());
        Order order3 = new Order(1, "用户订单", 2, productService.getProduct());
        Order order4 = new Order(1, "用户订单", 2, productService.getProduct());
        Order order5 = new Order(1, "用户订单", 2, productService.getProduct());
        List<Order> list = new ArrayList<>();
        list.add(order1);
        list.add(order2);
        list.add(order3);
        list.add(order4);
        list.add(order5);
        return list;
    }


    @Override
    public Order getOrderById() {

        Order order = new Order(1, "用户订单", 2, productService.getProduct());
        return order;
    }

    //通过DiscoveryClient来获取远程调用的结果
    private List<Product> selectProductDiscoveryClient() {
        //获取注册中心所有服务
        List<String> services = discoveryClient.getServices();
        //根据服务名称从注册中心获取服务
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("product-server");
        String host = serviceInstances.get(0).getHost();
        int port = serviceInstances.get(0).getPort();
        String url = "http://" + host + ":" + port + "/product";
        ResponseEntity<List<Product>> exchange = restTemplate.exchange(
                url,        //访问路径
                HttpMethod.GET,     //请求方式
                null,   //请求参数
                new ParameterizedTypeReference<List<Product>>() {   //返回的类型
                });
        return exchange.getBody();

    }

    //通过LoadBalancerClient获取远程调用的结果
    private List<Product> selectProductLoadBalancerClient() {

        //根据服务名称从注册中心获取服务
        ServiceInstance serviceInstance = loadBalancerClient.choose("product-server");
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url = "http://" + host + ":" + port + "/product";
        System.out.println(url);
        ResponseEntity<List<Product>> exchange = restTemplate.exchange(
                url,        //访问路径
                HttpMethod.GET,     //请求方式
                null,   //请求参数
                new ParameterizedTypeReference<List<Product>>() {   //返回的类型
                });
        return exchange.getBody();

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
