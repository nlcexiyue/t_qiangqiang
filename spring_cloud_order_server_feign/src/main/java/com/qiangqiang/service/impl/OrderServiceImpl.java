package com.qiangqiang.service.impl;


import com.qiangqiang.entity.Order;
import com.qiangqiang.service.OrderService;
import com.qiangqiang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;


    @Override
    public Order getOrder(Integer id) {
        System.out.println(Thread.currentThread().getName() + "------------");
        if (id == 1) {
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
        System.out.println(Thread.currentThread().getName() + "------------");
        Order order = new Order(1, "用户订单", 2, productService.getProduct());
        return order;
    }

    public Order undertake() {
        Order order = new Order(1, "用户订单兜底兜底", 2, productService.getProduct());
        return order;
    }


}
