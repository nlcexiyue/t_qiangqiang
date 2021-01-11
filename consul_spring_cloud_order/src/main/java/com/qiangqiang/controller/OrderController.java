package com.qiangqiang.controller;

import com.qiangqiang.entity.Order;
import com.qiangqiang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order")
    public Order getOrder() {

        Order orderById = orderService.getOrderById();
        return orderById;
    }
}
