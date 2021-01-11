package com.qiangqiang.service.impl;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.qiangqiang.entity.Order;
import com.qiangqiang.service.OrderService;
import com.qiangqiang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @HystrixCommand(
            commandProperties = {
                    //10s内请求大于10就启动熔断器，当符合熔断条件触发 fallbackMethod默认20个
                    @HystrixProperty(name= HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,value = "10"),
                    //请求错误率大于50%就启动熔断器，然后for循环发起重试请求，当请求符合熔断条件触发fallbackMethod
                    @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,value = "50"),
                    //熔断多少秒后去重试请求，默认5s
                    @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,value = "5000")
            },
            fallbackMethod = "undertake1")        // 指定降级方法，在熔断和异常时会走降级方法
    @Override
    public Order getOrder(Integer id) {
        System.out.println(Thread.currentThread().getName()+"------------");
        if(id == 1){
            throw new RuntimeException("熔断了要");
        }
        Order order = new Order(1, "用户订单", 2, productService.getProductById(id));
        return order;
    }


    public Order undertake1(Integer id) {
        Order order = new Order(1, "用户订单兜底兜底", 2, productService.getProduct());
        return order;
    }


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
