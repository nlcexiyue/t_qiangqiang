package com.qiangqiang.controller;

import com.qiangqiang.entity.Order;
import com.qiangqiang.service.OrderService;
import com.qiangqiang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;
    @RequestMapping("/order")
    public Order getOrder() {

        Order order = orderService.getOrderById();
        return order;
    }

    @RequestMapping("/orderById")
    public Order getOrder(Integer id) {

        Order order = orderService.getOrder(id);
        return order;
    }
}
